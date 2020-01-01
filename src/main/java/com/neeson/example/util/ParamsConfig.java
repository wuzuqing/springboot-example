package com.neeson.example.util;

public class ParamsConfig {
    public static String getUploadDir() {
        return getRootDir();
    }

    private static String getRootDir() {
        boolean isLinux = "Linux".equals(getOSName());
        if (isLinux) {
            return "/usr/local/javaproject/static/jim/";
        }
        return "F:/test/static/jim/";
    }

    /**
     * 直接获取系统名称 区分
     */

    private static String getOSName() {
        String osName = System.getProperties().getProperty("os.name");
//        if (osName.equals("Linux")) {
//            System.out.println("linux");
//        } else {
//            System.out.println("other");
//        }
        return osName;
    }
}
