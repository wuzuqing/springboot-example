package com.neeson.example.book.hsxs;

import com.neeson.example.book.AbsBookJSoupHelper;
import com.neeson.example.book.AbsDownload;
import com.neeson.example.book.DownloadListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HsxsMlDownload extends AbsDownload {

    public HsxsMlDownload(String bookPath, String bookName, int startIndex, int endIndex) {
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
        return new HsxsMuluHelper(bookFile, index);
    }

    @Override
    protected String genUrlPath() {
        return "%s%d.html";
    }

    @Override
    protected boolean needMerge() {
        return true;
    }

    public static void main(String[] args) {
        //6562
        String path = "/artlist/14-";
        String name = "家庭乱伦";
        int start = 2;
        int end = 900;
        List<Fenlei> fenleis = new ArrayList<>();
//        fenleis.add(new Fenlei(906,"/artlist/13-","都市言情"));
//        fenleis.add(new Fenlei(900,"/artlist/15-","人妻女友"));
//        fenleis.add(new Fenlei(902,"/artlist/16-","校园春色"));
        fenleis.add(new Fenlei(886,"/artlist/14-","家庭乱伦"));
//        fenleis.add(new Fenlei(888,"/artlist/17-","古典武侠"));
//        fenleis.add(new Fenlei(884,"/artlist/19-","另类小说"));
//        fenleis.add(new Fenlei(882,"/artlist/18-","性爱技巧"));
//        fenleis.add(new Fenlei(776,"/artlist/20-","情色笑话"));
//        fenleis.add(new Fenlei(810,"/artlist/21-","其它小说"));


        for (Fenlei fenlei : fenleis) {
            AbsDownload biXiaWenXue = new HsxsMlDownload(fenlei.path, fenlei.name, start, fenlei.end);
            biXiaWenXue.startDownload();
            biXiaWenXue.setListener(new DownloadListener() {
                @Override
                public void downloadFinish() {
                    AbsDownload text = new HsxsDownload(fenlei.path, fenlei.name, start, fenlei.end);
                    text.startDownload();
                }
            });
        }
    }

    private static class Fenlei{
        private int start =2;
        private int end;
        private String path;
        private String name;

        public Fenlei( int end, String path, String name) {
            this.end = end;
            this.path = path;
            this.name = name;
        }
    }


}
