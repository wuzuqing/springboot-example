package com.neeson.example.book.dswx;

import com.sun.deploy.net.URLEncoder;
import com.neeson.example.book.AbsDownload;
import com.neeson.example.book.AbsSearchBookHelper;
import com.neeson.example.book.Callback;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;

public class DswxSearchBookHelper extends AbsSearchBookHelper {

    public DswxSearchBookHelper(String bookName, Callback callback) {
        super(bookName, callback);
    }

    public DswxSearchBookHelper(String bookName, boolean autoDownload) {
        super(bookName, autoDownload);
    }

    @Override
    public String searchUrl(String bookName) {
        try {
            return "http://www.d3zww.com/modules/article/search.php?action=login&searchkey=" +  URLEncoder.encode(bookName, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://www.d3zww.com/modules/article/search.php?action=login";
    }

    @Override
    protected Elements getRootElements(Document document) {
        return document.getElementsByClass("bookinfo_intro");
    }

    @Override
    protected void parseResult(Element rootElement) {
        Elements elements = rootElement.getElementsByTag("a");
        Element element = elements.get(0);
        String text = element.text();
        String result = text.substring(text.indexOf(".com")+5);
        callResult(result);

    }

    @Override
    protected AbsDownload createDownload(String result, String bookName) {
        return new DiSanWenXue(result, bookName, 0, 1);
    }
}
