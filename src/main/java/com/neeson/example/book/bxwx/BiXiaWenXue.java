package com.neeson.example.book.bxwx;

import com.neeson.example.book.AbsDownload;
import com.neeson.example.book.AbsSearchBookHelper;
import com.neeson.example.book.Callback;

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

    public static void main(String[] args) {

        String searchKey = "乡村最强小神农";
        AbsSearchBookHelper searchBookHelper = new BxwxSearchBookHelper(searchKey, new Callback() {
            @Override
            public void call(Object o) {
                AbsDownload biXiaWenXue = new BiXiaWenXue(o.toString(), searchKey, 0, 100);
                biXiaWenXue.startDownload();
            }
        });
        searchBookHelper.run();
    }


}
