package com.neeson.example.service;

import com.neeson.example.entity.model.BookCatalogDto;
import com.neeson.example.entity.model.BookDto;

import java.util.List;
import java.util.TreeMap;

public interface IBookService {

    BookDto loadBook(final String bookName, String channel);

    List<BookCatalogDto> loadBookCatalogList(final String bookName, String channel);

    void saveCatalog(final String bookName,String channel, TreeMap<String, BookCatalogDto> list);

    void saveContent(String content, int index);

    void updateList( List<BookCatalogDto> list);

    List<BookCatalogDto> loadBookList(Long bookId, int page, int pageSize);

}
