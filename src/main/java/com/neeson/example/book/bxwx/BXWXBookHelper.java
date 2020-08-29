package com.neeson.example.book.bxwx;

import com.neeson.example.book.AbsBookJSoupHelper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class BXWXBookHelper extends AbsBookJSoupHelper {


    public BXWXBookHelper(File bookFile, int index) {
        this.bookFile = bookFile;
        this.index = index;
    }

    @Override
    public Elements getRootElements(Document document) {
        return document.getElementsByClass("box_con");
    }

    @Override
    protected String parseContent(Element rootElement) {
        Element contentElement = rootElement.getElementById("content");
        if(contentElement==null){
            return "加载失败，暂无内容";
        }
        return contentElement.html();
    }

    @Override
    protected String parseTitle(Element rootElement) {
        Elements bookName = rootElement.getElementsByClass("bookname");
        String zhang = null;
        if (bookName.size() > 0) {
            Elements h1 = bookName.get(0).getElementsByTag("h1");
            if (h1 != null && h1.size() > 0) {
                zhang = String.format("\n%s\n", h1.get(0).text());
            }
        }
        return zhang;
    }

    @Override
    protected void saveFile(String title, String content) {
        super.saveFile(title, content);
    }
}
