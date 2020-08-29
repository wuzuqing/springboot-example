package com.neeson.example.service.impl;

import com.neeson.example.book.util.Logger;
import com.neeson.example.entity.model.BookCatalogDto;
import com.neeson.example.entity.model.BookDto;
import com.neeson.example.repository.BookCatalogRepository;
import com.neeson.example.repository.BookRepository;
import com.neeson.example.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
@Slf4j
public class BookServiceImpl implements IBookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookCatalogRepository bookCatalogRepository;

    public BookDto loadBook(final String bookName, String channel) {
        BookDto book = bookRepository.findByNameAndChannel(bookName, channel);
        Logger.logAndCall(" loadBook" + book);
        return book;
    }

    @Override
    public List<BookCatalogDto> loadBookCatalogList(String bookName, String channel) {
        BookDto bookDto = loadBook(bookName, channel);
        if (bookDto != null) {
            return bookCatalogRepository.findByBookId(bookDto.getId());
        }
        return null;
    }

    private long bookId;

    @Override
    public void saveCatalog(final String bookName, String channel, TreeMap<String, BookCatalogDto> list) {

        BookDto bookModel = loadBook(bookName, channel);
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
        BookCatalogDto desc = bookCatalogRepository.findFirstByOrderByIdDesc();
        long id = 0;
        if (desc == null) {
            id = 1;
        } else {
            id = desc.getId() + 1;
        }
        for (String key : keys) {
            BookCatalogDto catalog = list.get(key);
            if (catalog != null) {
                model = new BookCatalogDto();
                model.setId(id);
                model.setPath(catalog.getPath());
                model.setTitle(catalog.getTitle());
                model.setCatalogIndex(catalog.getCatalogIndex());
                model.setBookId(bookModel.getId());
                result.add(model);
                id++;
            }
        }


        Collections.sort(result, new Comparator<BookCatalogDto>() {
            @Override
            public int compare(BookCatalogDto o1, BookCatalogDto o2) {
                return o1.getCatalogIndex() - o2.getCatalogIndex();
            }
        });
//        bookCatalogRepository.
        long start = System.currentTimeMillis();
        bookCatalogRepository.batchInsert(result);
        System.out.println("saveAll  used :" + (System.currentTimeMillis() - start));
    }

    @Override
    public void saveContent(String content, int index) {
        BookCatalogDto catalogModel = bookCatalogRepository.findByBookIdAndCatalogIndex(bookId, index);
        if (catalogModel != null) {
            Logger.logAndCall(catalogModel.toString());
            catalogModel.setContent(content);
            bookCatalogRepository.save(catalogModel);
        }
    }

    @Override
    public void updateList(List<BookCatalogDto> list) {
        long start = System.currentTimeMillis();
        bookCatalogRepository.batchUpdate(list);
        System.out.println("saveAll  used :" + (System.currentTimeMillis() - start));
    }
}
