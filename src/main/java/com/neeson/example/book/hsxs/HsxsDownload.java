package com.neeson.example.book.hsxs;

import com.neeson.example.book.AbsBookJSoupHelper;
import com.neeson.example.book.AbsDownload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HsxsDownload extends AbsDownload {

    public HsxsDownload(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
//        saveParent = saveParent+"/都市言情";
    }

    @Override
    protected String initHost() {
        return "http://www.yinyinai111.com";
    }

    @Override
    protected boolean canParseCatalog() {
        return false;
    }
    // 鸿蒙之始  "/b/55/55097" "鸿蒙之始" 2889764  860
    //洪荒之红云大道  b/136/136524/   6919782    // 7260998
    //网游之九转轮回  b/96/96125/     4908276             5146121   188199 188200


    @Override
    protected AbsBookJSoupHelper createHelper(File bookFile, int index) {
        return new HsxsHelper(bookFile, index);
    }

    protected void defaultCatalog() {
        int index = 1;
        File[] files = bookFile.listFiles();
        List<String> paths = new ArrayList<>();
        if (files != null) {
            System.out.println(files[0].getAbsolutePath());
            try {
                BufferedReader reader = new BufferedReader(new FileReader(files[0]));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    paths.add(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (paths.isEmpty()) {
            for (int i = startIndex; i <= endIndex; i++) {
                String path = String.format(genUrlPath(), bookPath, i);
                bookCatalogs.put(path, createCatalog(path, String.format("第%d章", index), index));
                index++;
            }
        } else {
//            System.out.println(paths.toString());
            for (String path : paths) {
                bookCatalogs.put(path, createCatalog(path, String.format("第%d章", index), index));
                index++;
            }
        }

    }

    @Override
    protected boolean needMerge() {
        return false;
    }

    public static void main(String[] args) {
        //6562
        String path = "/arts";
        String name = "都市言情";
        int start = 188199;
        int end = 188200;

        AbsDownload biXiaWenXue = new HsxsDownload(path, name, start, end);
        biXiaWenXue.startDownload();
    }


}
