package com.neeson.example.book;


import com.neeson.example.book.util.Logger;
import com.neeson.example.entity.model.BookCatalogDto;
import com.neeson.example.entity.model.BookDto;
import com.neeson.example.repository.BookCatalogRepository;
import com.neeson.example.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Slf4j
public class SqlHelper {
    private static SqlHelper sInstance = new SqlHelper();

    public static SqlHelper getInstance() {
        return sInstance;
    }

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCatalogRepository bookCatalogRepository;

    private BookDto loadBookCatalog(final String bookName) {
        BookDto book = bookRepository.findByName(bookName);
        Logger.logAndCall(" loadBookCatalog" + book);
        return book;
    }

    private long bookId;

    public void saveCatalog(final String bookName, TreeMap<String, BookCatalog> list) {

        BookDto bookModel = loadBookCatalog(bookName);
        if (ObjectUtils.isEmpty(bookModel)) {
            bookModel = new BookDto();
            bookModel.setName(bookName);
            bookRepository.saveAndFlush(bookModel);
        } else {
//            bookCatalogRepository.deleteByBookId(bookModel.getId());
        }
        bookId = bookModel.getId();
        Set<String> keys = list.keySet();
        BookCatalogDto model = null;
        final List<BookCatalogDto> result = new ArrayList<>();
        for (String key : keys) {
            BookCatalog catalog = list.get(key);
            if (catalog != null) {
                model = new BookCatalogDto();
                model.setPath(catalog.getPath());
                model.setTitle(catalog.getTitle());
                model.setCatalogIndex(catalog.getIndex());
                model.setBookId(bookModel.getId());
                result.add(model);
            }
        }


        Collections.sort(result, new Comparator<BookCatalogDto>() {
            @Override
            public int compare(BookCatalogDto o1, BookCatalogDto o2) {
                return o1.getCatalogIndex() - o2.getCatalogIndex();
            }
        });
        bookCatalogRepository.saveAll(result);
    }

    public void saveContent(String content, int index) {
        BookCatalogDto catalogModel = bookCatalogRepository.findByBookIdAndCatalogIndex(bookId, index);
        if (catalogModel != null) {
            Logger.logAndCall(catalogModel.toString());
            catalogModel.setContent(content);
            bookCatalogRepository.save(catalogModel);
        }
    }
}
