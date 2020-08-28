package com.neeson.example.book;

import com.neeson.example.book.bxwx.BiXiaWenXue;
import com.neeson.example.book.bxwx.BxwxSearchBookHelper;
import com.neeson.example.book.dswx.DiSanWenXue;
import com.neeson.example.book.dswx.DswxSearchBookHelper;
import com.neeson.example.book.jawx.JawxSearchBookHelper;
import com.neeson.example.book.jawx.JiuAiWenXue;
import com.neeson.example.entity.model.BookCatalogDto;

public class SearchBookUtil {

    public static void main(String[] args) {
        String searchKey = "网游之九转轮回";
        SearchBookUtil.search(searchKey);
    }

    public static void search(String bookName) {
        AbsSearchBookHelper searchBookHelper = new BxwxSearchBookHelper(bookName, true);
        searchBookHelper.run();
    }

    public static void downloadOne(BookCatalogDto catalogModel, DownloadListener listener) {
        String type = "笔下";
        switch (type) {
            case "第三":
                new DiSanWenXue("11", "11", 0, 1).setListener(listener).downloadOne(catalogModel);
                break;
            case "就爱":
                new JiuAiWenXue("11", "11", 0, 1).setListener(listener).downloadOne(catalogModel);
                break;
            default:
                new BiXiaWenXue("11", "11", 0, 1).setListener(listener).downloadOne(catalogModel);
                break;
        }

    }


    public static void searchByType(String type, String bookName) {
        AbsSearchBookHelper searchBookHelper = new BxwxSearchBookHelper(bookName, true);
        switch (type) {
            case "笔下":
                searchBookHelper = new BxwxSearchBookHelper(bookName, true);
                break;
            case "第三":
                searchBookHelper = new DswxSearchBookHelper(bookName, true);
                break;
            case "就爱":
                searchBookHelper = new JawxSearchBookHelper(bookName, true);
                break;
            default:
                break;
        }
        searchBookHelper.run();
    }
}
