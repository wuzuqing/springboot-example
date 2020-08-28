package com.neeson.example.book;


import com.neeson.example.book.util.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

public abstract class AbsSearchBookHelper implements Runnable {
    protected String bookName;

    private Callback callback;
    protected boolean autoDownload;

    public abstract String searchUrl(String bookName);

    public AbsSearchBookHelper(String bookName, Callback callback) {
        this.bookName = bookName;
        this.callback = callback;
    }

    public AbsSearchBookHelper(String bookName, boolean autoDownload) {
        this.bookName = bookName;
        this.autoDownload = autoDownload;
    }

    @Override
    public void run() {
        System.out.println(bookName);
        JSoupHelper helper = new JSoupHelper() {

            @Override
            public Elements getRootElements(Document document) {
                return AbsSearchBookHelper.this.getRootElements(document);
            }

            @Override
            public void anaylizeRootElement(Element rootElement) {
                parseResult(rootElement);

            }
        };

        try {
            String url = searchUrl(bookName);
            Logger.d("url= " + url);
            helper.setDocument(genDocument(url)).startAnaylizeByJsoup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document genDocument(String url) {
        return getJsoupDocGet(url);
    }

    private static final int MAX = 3;

    public static Document getJsoupDocGet(String url) {
        //三次试错
        int time = 0;
        Document doc;
        while (time < MAX) {
            try {
                doc = createConnection(url).get();
                return doc;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                time++;
            }
        }
        return null;
    }


    public static Document getJsoupDocPost(String url, Map<String, String> paramMap) {
        //三次试错
        int time = 0;
        Document doc;
        while (time < MAX) {
            try {
                doc = createConnection(url).data(paramMap).post();
                return doc;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                time++;
            }
        }
        return null;
    }

    private static Connection createConnection(String url) {
        return Jsoup
                .connect(url)
//                .ignoreContentType(true)
//                .ignoreHttpErrors(true)
//                .timeout(1000 * 30)
//                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
//                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
//                .header("accept-encoding", "gzip, deflate, br")
//                .header("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
                ;
    }

    protected abstract Elements getRootElements(Document document);

    protected abstract void parseResult(Element rootElement);


    protected void callResult(String result) {
        if (callback != null) {
            callback.call(result);
        }
        if (autoDownload) {
            Logger.logAndCall(result);
            createDownload(result, bookName).startDownload();
        }
    }

    protected abstract AbsDownload createDownload(String result, String bookName);

}
