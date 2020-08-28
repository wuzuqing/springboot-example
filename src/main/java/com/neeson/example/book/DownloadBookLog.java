package com.neeson.example.book;

public class DownloadBookLog {
    private static Callback<String> listener;

    public static void setListener(Callback<String> listener) {
        DownloadBookLog.listener = listener;
    }

    public static void callLog(String msg){
        if (listener!=null){
            listener.call(msg);
        }
    }
}
