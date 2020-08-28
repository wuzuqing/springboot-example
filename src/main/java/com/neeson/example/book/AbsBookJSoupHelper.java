package com.neeson.example.book;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/16.
 */

@Slf4j
public abstract class AbsBookJSoupHelper extends JSoupHelper {
    protected File bookFile;
    protected int index;
    protected String url;

    protected String currentZ() {
        return String.format("\n第%d张\n", index);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void anaylizeRootElement(Element rootElement) {
        String content = parseContent(rootElement);
        if (ObjectUtils.isEmpty(content)) return;
        String jie = parseTitle(rootElement);
        if (jie == null) {
            jie = currentZ();
        }
        saveFile(jie, content);
    }

    protected abstract String parseContent(Element rootElement);

    protected abstract String parseTitle(Element rootElement);

    protected void saveFile(String title, String content) {
        try {
            File tempFile = new File(bookFile, "" + index);
            FileOutputStream outputStream = new FileOutputStream(tempFile, false);
            outputStream.write(title.getBytes("utf-8")); //第几章
            byte[] bytes = content.getBytes("utf-8");
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}