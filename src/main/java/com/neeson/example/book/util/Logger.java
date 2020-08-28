package com.neeson.example.book.util;


import com.neeson.example.book.DownloadBookLog;
import com.neeson.example.book.OSInfo;

public class Logger {
    private static boolean L_DEBUG = true;
    private static ILogger sLogger;

    public static void setLogger(ILogger logger){
        sLogger = logger;
    }

    public static void i(String msg) {
        if (L_DEBUG) {
            initLogger();
            sLogger.i( msg);
        }
    }

    private static void initLogger() {
        if (sLogger==null){
            sLogger = new JavaLogger();
        }
    }

    public static void d(String msg) {
        if (L_DEBUG) {
            initLogger();
            sLogger.d( msg);
        }
    }

    public static void w(String msg) {
        if (L_DEBUG) {
            initLogger();
            sLogger.w( msg);
        }
    }

    public static void e(String msg) {
        if (L_DEBUG) {
            initLogger();
            sLogger.e( msg);
        }
    }

    public static void v(String msg) {
        if (L_DEBUG) {
            initLogger();
            sLogger.v( msg);
        }
    }

    public static void logAndCall(String msg) {
        if (!OSInfo.isWindows()){
            DownloadBookLog.callLog(msg);
        }
        d(msg);
    }
}
