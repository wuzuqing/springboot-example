package com.neeson.example.book.dswx;

import com.neeson.example.book.AbsBookJSoupHelper;
import com.neeson.example.book.AbsDownload;
import com.neeson.example.book.BookCatalog;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

public class DiSanWenXue extends AbsDownload {

    public DiSanWenXue(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
    }

    @Override
    protected Elements parseCatalogTag(Document document) {
        Elements list = document.getElementsByClass("book_list");
        if (list == null) return null;
        return list.select("a[href]");
    }


    @Override
    protected boolean otherTitle(String title) {
        System.out.println(title);
        return true;
    }

    @Override
    protected String initHost() {
        return "http://www.d3zww.com/";
    }


    @Override
    protected boolean canParseCatalog() {
        return true;
    }

    @Override
    protected BookCatalog createCatalog(String path, String title, int index) {
        return new BookCatalog(title, path, index);
    }

    @Override
    protected boolean checkPath(String path) {
        return true;
    }

    @Override
    protected AbsBookJSoupHelper createHelper(File bookFile, int index) {
        return new DSWXBookHelper(bookFile, index);
    }

    public static void main(String[] args) {
        //6562
        String name = "洪荒之度厄圣人";
        new DswxSearchBookHelper(name, true).run();
    }
}
