package com.neeson.example.book.jawx;

import com.neeson.example.book.AbsBookJSoupHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class JAWXBookHelper extends AbsBookJSoupHelper {


    public JAWXBookHelper(File bookFile, int index) {
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
        return contentElement.text();
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

}
