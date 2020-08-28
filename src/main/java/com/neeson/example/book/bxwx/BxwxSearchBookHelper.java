package com.neeson.example.book.bxwx;

import com.neeson.example.book.AbsDownload;
import com.neeson.example.book.AbsSearchBookHelper;
import com.neeson.example.book.Callback;
import org.apache.commons.lang.ObjectUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class BxwxSearchBookHelper extends AbsSearchBookHelper {

    public BxwxSearchBookHelper(String bookName, boolean autoDownload) {
        super(bookName, autoDownload);
    }

    public BxwxSearchBookHelper(String bookName, Callback callback) {
        super(bookName, callback);
    }

    @Override
    public String searchUrl(String bookName) {
        return "https://www.bxwxorg.com/search.html";
    }

    @Override
    protected Document genDocument(String url) {
        Map<String,String > params = new HashMap<>(2);
        params.put("searchtype","all");
        params.put("searchkey",bookName);
        return getJsoupDocPost(url,params);
    }

    @Override
    protected Elements getRootElements(Document document) {
        return document.getElementsByClass("coverecom w_730 left");
    }

    @Override
    protected void parseResult(Element rootElement) {
        Elements elements = rootElement.getElementsByTag("a");
        String href = "";
        String value = "";
        for (Element element : elements) {
            value = element.text();
            if (ObjectUtils.equals(value, bookName)) {
                href = element.attr("href");
                callResult(href);
                return;
            }
        }
    }

    @Override
    protected AbsDownload createDownload(String result, String bookName) {
        return new BiXiaWenXue(result, bookName, 0, 1);
    }

}
