package com.neeson.example.book.hsxs;

import com.neeson.example.book.AbsBookJSoupHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class HsxsHelper extends AbsBookJSoupHelper {


    public HsxsHelper(File bookFile, int index) {
        this.bookFile = bookFile;
        this.index = index;
    }

    @Override
    public Elements getRootElements(Document document) {
        return document.getElementsByClass("artbody");
    }

    @Override
    protected String parseContent(Element rootElement) {
        Elements cont = rootElement.getElementsByClass("cont");
        if (cont.size() > 0) {
//            System.out.println("zhang:"+cont.get(0).text());
            return cont.get(0).text();
        }
        return "";
    }

    @Override
    protected String parseTitle(Element rootElement) {
        Elements bookName = rootElement.getElementsByClass("arttit");
        String zhang = null;
        if (bookName.size() > 0) {
            zhang = bookName.get(0).text();
//            Elements h1 = bookName.get(0).getElementsByTag("h1");
//            if (h1 != null && h1.size() > 0) {
//                zhang = String.format("\n%s\n", h1.get(0).text());
//            }
        }
        return zhang;
    }


    @Override
    protected void saveFile(String title, String content) {
        try {
            int zuozhe = title.indexOf("{");
            if (zuozhe==-1){
                zuozhe =title.indexOf("作者");
            }
            String child;
            if (zuozhe > 0) {
                child = "/" + title.substring(0, zuozhe) + ".txt";
            } else {
                child = "/" + title + ".txt";
            }
            child = child.trim().replaceAll("[|\\\\]","");
            File tempFile = new File(bookFile, child);
            FileOutputStream outputStream = new FileOutputStream(tempFile, false);
//            outputStream.write(title.getBytes("utf-8")); //第几章
            byte[] bytes = content.getBytes("utf-8");
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
