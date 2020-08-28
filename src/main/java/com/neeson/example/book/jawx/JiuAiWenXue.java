package com.neeson.example.book.jawx;

import com.neeson.example.book.*;
import com.neeson.example.book.bxwx.BxwxSearchBookHelper;
import com.neeson.example.book.dswx.DSWXBookHelper;

import java.io.File;

public class JiuAiWenXue extends AbsDownload {

    public JiuAiWenXue(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
    }

    @Override
    protected String initHost() {
        return "https://www.9awx.com";
    }

    @Override
    protected boolean canParseCatalog() {
        return true;
    }


    protected boolean checkPath(String path) {
        return !bookCatalogs.containsKey(path);
    }

    @Override
    protected BookCatalog createCatalog(String path, String title, int index) {
        return new BookCatalog(title, bookPath+ path, index);
    }

    public static void main(String[] args) {

        String searchKey = "万古最强宗";
        AbsSearchBookHelper searchBookHelper = new JawxSearchBookHelper(searchKey, true);
        searchBookHelper.run();
    }

    @Override
    protected AbsBookJSoupHelper createHelper(File bookFile, int index) {
        return new JAWXBookHelper(bookFile, index);
    }
}
