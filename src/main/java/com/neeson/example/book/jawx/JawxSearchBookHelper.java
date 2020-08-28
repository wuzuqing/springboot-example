package com.neeson.example.book.jawx;

import com.neeson.example.book.AbsDownload;
import com.neeson.example.book.AbsSearchBookHelper;
import com.neeson.example.book.Callback;
import org.apache.commons.lang.ObjectUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class JawxSearchBookHelper extends AbsSearchBookHelper {

    public JawxSearchBookHelper(String bookName, boolean autoDownload) {
        super(bookName, autoDownload);
    }

    public JawxSearchBookHelper(String bookName, Callback callback) {
        super(bookName, callback);
    }

    @Override
    public String searchUrl(String bookName) {
        try {
            return "https://www.9awx.com/modules/article/search.php?searchkey=" +  URLEncoder.encode(bookName, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "https://www.9awx.com/modules/article/search.php?searchkey=" + bookName;
    }
    private String result;
    @Override
    protected Elements getRootElements(Document document) {
        Elements elements = document.head().getElementsByTag("meta");
        for (Element element : elements) {
            String property = element.attr("property");
            if (ObjectUtils.equals("og:url",property)){
                result = element.attr("content").replaceAll("https://www.9awx.com","");
            }
        }
        return document.getElementsByClass("box_con");
    }
    private int index = 0;
    @Override
    protected void parseResult(Element rootElement) {
        if (index==0){
            callResult(result);
        }
        index++;
    }

    @Override
    protected AbsDownload createDownload(String result, String bookName) {
        return new JiuAiWenXue(result, bookName, 0, 1);
    }

}
