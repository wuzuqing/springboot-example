package com.neeson.example.repository;

import com.neeson.example.entity.model.BookCatalogDto;
import com.neeson.example.repository.base.BaseRepository;

import java.util.List;

public interface BookCatalogRepository extends BaseRepository<BookCatalogDto, Long> {
//    void deleteByBookId(Long bookId);

    BookCatalogDto findByBookIdAndCatalogIndex(Long bookId,int index);

    BookCatalogDto findFirstByOrderByIdDesc();

    List<BookCatalogDto> findByBookId(Long bookId);

    List<BookCatalogDto> findByBookIdAndContentIsNull(Long bookId);
}