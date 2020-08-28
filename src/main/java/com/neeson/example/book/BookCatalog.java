package com.neeson.example.book;


import lombok.Data;

@Data
public class BookCatalog {
    private String title;
    private String path;
    private int index;

    public BookCatalog(String title, String path, int index) {
        this.title = title;
        this.path = path;
        this.index = index;
    }
}
