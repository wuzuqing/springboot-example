package com.neeson.example.book;


import org.jsoup.nodes.Element;

import java.io.File;

/**
 * Created by Administrator on 2016/6/16.
 */

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
            if (!OSInfo.isWindows()){
                SqlHelper.getInstance() .saveContent(content,index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}