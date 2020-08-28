package com.neeson.example.repository;

import com.neeson.example.entity.model.BookDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookDto, Long> {
    BookDto findByName(String bookName);
}