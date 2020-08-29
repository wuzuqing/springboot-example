package com.neeson.example.book;


import com.neeson.example.entity.model.BookCatalogDto;
import com.neeson.example.entity.model.BookDto;
import com.neeson.example.service.IBookService;

import java.util.List;
import java.util.TreeMap;

public class SqlHelper {
    private static SqlHelper sInstance = new SqlHelper();
    private IBookService bookService;

    public static SqlHelper getInstance() {
        return sInstance;
    }

    public BookDto loadBook(final String bookName, String channel) {
        return bookService.loadBook(bookName, channel);
    }

    public List<BookCatalogDto> loadBookCatalogList(final String bookName, String channel) {
        return bookService.loadBookCatalogList(bookName, channel);
    }

    public void saveCatalog(final String bookName, String channel, TreeMap<String, BookCatalogDto> list) {
        bookService.saveCatalog(bookName, channel, list);
    }

    public void saveContent(String content, int index) {
        bookService.saveContent(content, index);
    }

    public void initBookService(IBookService service) {
        this.bookService = service;
    }


    public void updateList(List<BookCatalogDto> list) {
        bookService.updateList(list);
    }
}
