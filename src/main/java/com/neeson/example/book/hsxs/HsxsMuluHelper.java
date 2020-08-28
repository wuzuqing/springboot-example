package com.neeson.example.book.hsxs;

import com.neeson.example.book.AbsBookJSoupHelper;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;

public class HsxsMuluHelper extends AbsBookJSoupHelper {


    public HsxsMuluHelper(File bookFile, int index) {
        this.bookFile = bookFile;
        this.index = index;
    }

    @Override
    public Elements getRootElements(Document document) {
        return document.getElementsByClass("textlist");
    }

    @Override
    protected String parseContent(Element rootElement) {
        Elements list = rootElement.getElementsByClass("body");
        StringBuilder sb = new StringBuilder();
        if (list.size() > 0) {
            for (Element element : list) {
                Element a = element.getElementsByTag("a").get(0);
                String href = a.attr("href");
                if (!StringUtils.isBlank(href)){
                    sb.append(href).append("\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    protected String parseTitle(Element rootElement) {
//        Elements bookName = rootElement.getElementsByClass("arttit");
        String zhang = null;
//        if (bookName.size() > 0) {
//            zhang = bookName.get(0).text();
////            Elements h1 = bookName.get(0).getElementsByTag("h1");
////            if (h1 != null && h1.size() > 0) {
////                zhang = String.format("\n%s\n", h1.get(0).text());
////            }
//        }
        return zhang;
    }


    @Override
    protected void saveFile(String title, String content) {
        try {
            File tempFile = new File(bookFile, index+"");
            FileOutputStream outputStream = new FileOutputStream(tempFile, false);
//            outputStream.write(title.getBytes("utf-8")); //第几章
            byte[] bytes = content.getBytes("utf-8");
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
