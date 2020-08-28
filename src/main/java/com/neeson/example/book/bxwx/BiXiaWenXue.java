package com.neeson.example.book.bxwx;


import com.neeson.example.book.AbsDownload;

public class BiXiaWenXue extends AbsDownload {

    public BiXiaWenXue(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
    }

    @Override
    protected String initHost() {
        return "https://www.bxwxorg.com";
    }

    @Override
    protected boolean canParseCatalog() {
        return true;
    }


}
