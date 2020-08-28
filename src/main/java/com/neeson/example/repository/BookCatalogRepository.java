package com.neeson.example.repository;

import com.neeson.example.entity.model.BookCatalogDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCatalogRepository extends JpaRepository<BookCatalogDto, Long> {
//    void deleteByBookId(Long bookId);

    BookCatalogDto findByBookIdAndCatalogIndex(Long bookId,int index);
}