package com.neeson.example.book.util;

public class JavaLogger implements ILogger {

    @Override
    public void i(String msg) {
        System.out.println(msg);
    }

    @Override
    public void d(String msg) {
        System.out.println(msg);
    }

    @Override
    public void e(String msg) {
        System.out.println(msg);
    }

    @Override
    public void w(String msg) {
        System.out.println(msg);
    }

    @Override
    public void v(String msg) {
        System.out.println(msg);
    }
}
