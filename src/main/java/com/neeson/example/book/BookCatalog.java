package com.neeson.example.book;



public class BookCatalog {
    private String title;
    private String path;
    private int index;

    public BookCatalog(String title, String path, int index) {
        this.title = title;
        this.path = path;
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
