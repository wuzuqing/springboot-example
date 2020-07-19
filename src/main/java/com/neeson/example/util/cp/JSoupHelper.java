package com.neeson.example.util.cp;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */

@Slf4j
public abstract class JSoupHelper {


    private Document document;

    public JSoupHelper setDocument(Document document) {
        this.document = document;
        return this;
    }

    public void startAnaylizeByJsoup() {
        Elements rootElements = getRootElements(document);
        for (Element rootElement : rootElements) {
            anaylizeRootElement(rootElement);
        }
    }

    /**
     * 获取解析的根目录集合
     *
     * @param document
     * @return
     */
    public abstract Elements getRootElements(Document document);

    /**
     * 根据每个根布局生成对应的java对象
     *
     * @param rootElement
     * @return
     */
    public abstract void anaylizeRootElement(Element rootElement);


    /**
     * 递归解析标签
     *
     * @param element
     * @param tags    标签的依次搜索规则
     * @return
     */
    public static Element paraseElement(Element element, List<String> tags) {
        if (tags != null && tags.size() > 0) {
            String parseTag = tags.get(0);
            Elements elements = element.getElementsByTag(parseTag);

            boolean isElementsNotEmpty = elements != null && elements.size() > 0;
            //  lg.e("解析标签：" + parseTag + ",Size is " + (isElementsNotEmpty ? elements.size() : 0));
            if (isElementsNotEmpty) {
                return paraseElement(elements.first(), tags.subList(1, tags.size()));
            } else {
                //    lg.e("该标签下的Element集合为空,return null");
                return null;
            }
        } else {
            // lg.e("找到指定元素");
            return element;
        }
    }


}