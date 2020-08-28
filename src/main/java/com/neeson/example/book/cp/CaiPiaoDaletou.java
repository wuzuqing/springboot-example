package com.neeson.example.book.cp;

import com.neeson.example.book.AbsBookJSoupHelper;
import com.neeson.example.book.AbsDownload;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaiPiaoDaletou extends AbsDownload {

    public CaiPiaoDaletou(String bookPath, String bookName, int startIndex, int endIndex) {
        super(bookPath, bookName, startIndex, endIndex);
    }

    @Override
    protected Elements parseCatalogTag(Document document) {
        Elements list = document.getElementsByClass("book_list");
        if (list == null) return null;
        return list.select("a[href]");
    }


    @Override
    protected boolean otherTitle(String title) {
        System.out.println(title);
        return true;
    }

    @Override
    protected String initHost() {
        return "https://www.lottery.gov.cn";
    }


    @Override
    protected boolean canParseCatalog() {
        return true;
    }


    private static void updateRecord(Integer page) {
        Document doc;
        AbsBookJSoupHelper helper;
        try {
            helper = new AbsBookJSoupHelper() {
                List<DltCpResult> result = new ArrayList<>();

                @Override
                protected String parseContent(Element rootElement) {
                    Elements elements = rootElement.getElementsByTag("tbody");
                    elements = elements.get(0).getElementsByTag("tr");
                    DltCpResult dltCpResult;
                    for (Element element : elements) {
                        try {
                            String[] split = element.text().split(" ");
                            if (split.length == 0) {
                                continue;
                            }
                            dltCpResult = new DltCpResult();
                            dltCpResult.setQiNo(split[0]);
                            dltCpResult.setDate(split[split.length - 1]);

                            dltCpResult.setBlueNum1(Integer.parseInt(split[1]));
                            dltCpResult.setBlueNum2(Integer.parseInt(split[2]));
                            dltCpResult.setBlueNum3(Integer.parseInt(split[3]));
                            dltCpResult.setBlueNum4(Integer.parseInt(split[4]));
                            dltCpResult.setBlueNum5(Integer.parseInt(split[5]));
                            dltCpResult.setRedNum1(Integer.parseInt(split[6]));
                            dltCpResult.setRedNum2(Integer.parseInt(split[7]));
                            result.add(dltCpResult);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    return "finish";
                }

                @Override
                protected String parseTitle(Element rootElement) {
                    return "大乐透开奖记录";
                }

                @Override
                public Elements getRootElements(Document document) {
                    return document.getElementsByClass("result");
                }

                @Override
                protected void saveFile(String title, String content) {
                    System.out.println("title:" + title);
                    for (DltCpResult cpResult : result) {
                        System.out.println(cpResult);
                    }
//                    System.out.println("content:" + content);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (result.size() == 20) {
                        System.out.println("准备查询下一页");
                        updateRecord(page + 1);
                    }
                }
            };
            String pagePath = page == 1 ? "history" : "history_" + page;
            doc = Jsoup.connect("https://www.lottery.gov.cn/historykj/"+pagePath+".jspx?_ltype=dlt").get();
            if (doc != null) {
                helper.setDocument(doc).startAnaylizeByJsoup();
            }
        } catch (IOException e) {
            e.printStackTrace();
            updateRecord(page);
        }
    }


    public static void main(String[] args) {
        //6562
        updateRecord(1);
    }
}
