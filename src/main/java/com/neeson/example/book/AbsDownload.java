package com.neeson.example.book;


import com.neeson.example.book.bxwx.BXWXBookHelper;
import com.neeson.example.book.util.Logger;
import com.neeson.example.entity.model.BookCatalogDto;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public abstract class AbsDownload {
    protected String host;
    protected String saveParent = "d://book/";
    protected String bookPath;
    protected static int startIndex;
    protected int endIndex;
    private int MAX_POOL = 15;
    protected File bookFile;
    private String bookName;
    private static int endCount;
    private boolean isHeBinging = false;
    protected TreeMap<String, BookCatalogDto> bookCatalogs;
    private int max;
    private DownloadListener listener;

    /**
     * @param bookPath   路径
     * @param bookName   书的名称
     * @param startIndex 开始的位置
     * @param endIndex   最后一张的位置
     */
    public AbsDownload(String bookPath, String bookName, int startIndex, int endIndex) {
        this.host = initHost();
        this.bookPath = bookPath;
        this.bookName = bookName;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        String os = System.getProperty("os.name");
        if (!os.toLowerCase().startsWith("win")) {
//            saveParent = BaseApp.getAppContext().getCacheDir().getAbsolutePath();
        }
    }

    protected abstract String initHost();

    protected abstract boolean canParseCatalog();

    public AbsDownload setListener(DownloadListener listener) {
        this.listener = listener;
        return this;
    }
    private String channel;

    public AbsDownload setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    protected Elements parseCatalogTag(Document document) {
        Element list = document.getElementById("list");
        if (list == null) return null;
        return list.select("a[href]");
    }

    private Runnable downloadTask = new Runnable() {
        @Override
        public void run() {
            AbsBookJSoupHelper helper;
            Document doc;
            while (!bookCatalogs.isEmpty()) { //判断是否结束
                helper = createHelper();
                try {
                    System.out.println("downloadTask:" + helper.getUrl());
                    doc = Jsoup.connect(helper.getUrl()).get();
                    if (doc != null) {
                        helper.setDocument(doc).startAnaylizeByJsoup();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            endCount++;
            if (needMerge() && endCount >= max) { //是否最后一个线程结束
//                heBinging();
                if (listener != null) {
                    listener.downloadFinish();
                }
                if (!list.isEmpty()) {
                    SqlHelper.getInstance().updateList( list);
                }
                System.out.println("downloadUsed:"+(System.currentTimeMillis()-startTime));
            }
        }
    };

    protected boolean needMerge() {
        return true;
    }

    public void startDownload() {
        if (bookFile == null) {
            bookFile = new File(saveParent + bookName);
            bookFile.mkdirs();
        } else if (!bookFile.exists()) {
            bookFile.mkdirs();
        }

        bookCatalogs = new TreeMap<>();
        if (canParseCatalog()) {
            downBookCatalog();
        } else {
            defaultCatalog();
        }
        start();
    }

    public void downloadOne(BookCatalogDto catalogModel) {

        bookCatalogs = new TreeMap<>();
        bookCatalogs.put(catalogModel.getPath(), catalogModel);
        if (bookCatalogs.isEmpty()) return;
        isHeBinging = false;
        endCount = 0;
        startTime = System.currentTimeMillis();
        Logger.logAndCall("开始下载 : " + startTime);
        // 保存书籍
        max = bookCatalogs.size() > MAX_POOL ? MAX_POOL : bookCatalogs.size();
        for (int i = 0; i < max; i++) {
            new Thread(downloadTask).start();
        }
    }


    protected void start() {
        if (bookCatalogs.isEmpty()) return;
        isHeBinging = false;
        list.clear();
        endCount = 0;
        startTime = System.currentTimeMillis();
        Logger.logAndCall("开始下载 : " + startTime);
        // 保存书籍
        if (needSaveCatalog) {
            SqlHelper.getInstance().saveCatalog(bookName,channel, bookCatalogs);
        }
        max = bookCatalogs.size() > MAX_POOL ? MAX_POOL : bookCatalogs.size();
        for (int i = 0; i < max; i++) {
            new Thread(downloadTask).start();
        }
    }

    private long startTime;
    boolean canStart = false;
    private int index = 1;

    protected void parseCatalogValue(Element rootElement) {
        String title = rootElement.text();

        if (!canStart && (title.startsWith("第一章") || title.startsWith("第1章") || otherTitle(title))) {
            canStart = true;
        }
        if (canStart) {
            String path = rootElement.attr("href");
            if (checkPath(path)) {
                bookCatalogs.put(path, createCatalog(path, title, index));
                index++;
            }
        }
    }

    protected boolean otherTitle(String title) {
        return false;
    }

    private boolean needSaveCatalog = true;

    /**
     * 下载目录
     */
    private void downBookCatalog() {
        // 检查是否有下载
        long start = System.currentTimeMillis();
        List<BookCatalogDto> list = SqlHelper.getInstance().loadBookCatalogList(bookName,channel);
        if (list != null) {
            int index = 0;
            for (BookCatalogDto catalogDto : list) {
                boolean hasNotContent = StringUtils.isEmpty(catalogDto.getContent());
                if (hasNotContent) {
                    canStart = true;
                    bookCatalogs.put(catalogDto.getPath(), catalogDto);
                }
            }
            System.out.println("loadBookCatalogList: size:" +bookCatalogs.size() + " used:" +(System.currentTimeMillis()-start));
            needSaveCatalog = false;
            return;
        }

        JSoupHelper helper = new JSoupHelper() {

            @Override
            public Elements getRootElements(Document document) {
                return parseCatalogTag(document);
            }

            @Override
            public void anaylizeRootElement(Element rootElement) {
                parseCatalogValue(rootElement);
            }
        };

        try {
            Document document;
            String url = genRealUrl(bookPath);
            Logger.logAndCall("url:" + url);
            Connection connect = Jsoup.connect(url);
            document = connect.get();
            helper.setDocument(document).startAnaylizeByJsoup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean checkPath(String path) {
        return !bookCatalogs.containsKey(path) && path.contains(bookPath);
    }

    protected void defaultCatalog() {
        int index = 1;
        for (int i = startIndex; i <= endIndex; i++) {
            String path = String.format(genUrlPath(), bookPath, i);
            bookCatalogs.put(path, createCatalog(path, String.format("第%d章", index), index));
            index++;
        }
    }

    protected String genUrlPath() {
        return "%s/%d.html";
    }


    protected BookCatalogDto createCatalog(String path, String title, int index) {
        return new BookCatalogDto(title, path, index);
    }

    private List<BookCatalogDto> list = new ArrayList<>();
    /**
     * url 拼接格式  host - path - index - .html
     *
     * @return 解析器
     */
    private synchronized AbsBookJSoupHelper createHelper() {
        BookCatalogDto bookCatalog = bookCatalogs.remove(bookCatalogs.firstKey());
        AbsBookJSoupHelper helper = createHelper(bookFile, bookCatalog.getCatalogIndex());
        helper.setBookCatalog(bookCatalog);
        if (!needSaveCatalog){
            list.add(bookCatalog);
        }
        helper.setUrl(genRealUrl(bookCatalog.getPath()));
        return helper;
    }

    private String genRealUrl(String path) {
        String url;
        if (!StringUtils.isEmpty(path) && path.startsWith(host)) {
            url = path;
        } else {
            url = String.format("%s%s", host, path);
        }
        return url;
    }

    protected AbsBookJSoupHelper createHelper(File bookFile, int index) {
        return new BXWXBookHelper(bookFile, index);
    }


}
