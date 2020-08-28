package com.neeson.example.book;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
            Document document;
            String url = searchUrl(bookName);
            System.out.println("url:" + url);
            Connection connect = Jsoup.connect(url);
            document = connect.get();
            helper.setDocument(document).startAnaylizeByJsoup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract Elements getRootElements(Document document);

    protected abstract void parseResult(Element rootElement);


    protected void callResult(String result) {
        if (callback != null) {
            callback.call(result);
        }
        if (autoDownload){
            System.out.println(result);
            createDownload(result, bookName).startDownload();
        }
    }

    protected abstract AbsDownload createDownload(String result, String bookName);

}
