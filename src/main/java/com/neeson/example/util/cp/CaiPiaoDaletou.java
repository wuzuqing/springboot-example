package com.neeson.example.util.cp;

import com.neeson.example.entity.DltCpDto;
import com.neeson.example.entity.DltCpExtendDto;
import com.neeson.example.repository.DltCpExtendRepository;
import com.neeson.example.repository.DltCpRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CaiPiaoDaletou {
    private List<DltCpDto> result = new ArrayList<>();


    public CaiPiaoDaletou(DltCpRepository dltCpRepository,DltCpExtendRepository dltCpExtendRepository) {
        this.dltCpRepository = dltCpRepository;
        this.dltCpExtendRepository = dltCpExtendRepository;
    }

    public CaiPiaoDaletou() {
    }

    private DltCpRepository dltCpRepository;
    DltCpExtendRepository dltCpExtendRepository;
    DltCpDto firstDltCpDto;

    public void updateRecord(Integer page) {
        Document doc;
        AbsBookJSoupHelper helper;
        try {
            helper = new AbsBookJSoupHelper() {
                List<DltCpDto> temp = new ArrayList<>();

                @Override
                protected String parseContent(Element rootElement) {
                    Elements elements = rootElement.getElementsByTag("tbody");
                    elements = elements.get(0).getElementsByTag("tr");
                    DltCpDto dltCpResult;
                    for (Element element : elements) {
                        try {
                            String[] split = element.text().split(" ");
                            if (split.length == 0) {
                                continue;
                            }
                            dltCpResult = new DltCpDto();
                            dltCpResult.setQiNo(Integer.valueOf(split[0]));
                            if (firstDltCpDto != null && firstDltCpDto.getQiNo().equals(dltCpResult.getQiNo())) {
                                break;
                            }
                            dltCpResult.setDate(split[split.length - 1]);

                            dltCpResult.setBlueNum1(Integer.parseInt(split[1]));
                            dltCpResult.setBlueNum2(Integer.parseInt(split[2]));
                            dltCpResult.setBlueNum3(Integer.parseInt(split[3]));
                            dltCpResult.setBlueNum4(Integer.parseInt(split[4]));
                            dltCpResult.setBlueNum5(Integer.parseInt(split[5]));
                            dltCpResult.setRedNum1(Integer.parseInt(split[6]));
                            dltCpResult.setRedNum2(Integer.parseInt(split[7]));

                            temp.add(dltCpResult);
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
                    for (DltCpDto cpResult : temp) {
                        System.out.println(cpResult);
                    }
                    result.addAll(temp);
                    if (temp.size() == 20) {
                        System.out.println("准备查询下一页");
                        updateRecord(page + 1);
                    } else {
                        if (result.size()>0){
                            Collections.reverse(result);
                            if (dltCpRepository != null) {
                                dltCpRepository.saveAll(result);
                            }
                             List<DltCpExtendDto> resultExtend = new ArrayList<>();
                            for (DltCpDto cpDto : result) {
                                resultExtend.add(new DltCpExtendDto(cpDto));
                            }
                            dltCpExtendRepository.saveAll(resultExtend);
                            // save
                            System.out.println("总保存记录数量：" + result.size());
                        }else{
                            System.out.println("暂无新的记录：");
                        }
                    }
                }
            };
            if (page == 1) {
                firstDltCpDto = dltCpRepository.findFirstByOrderByQiNoDesc();
                System.out.println(firstDltCpDto);
            }
            String pagePath = page == 1 ? "history" : "history_" + page;
            doc = Jsoup.connect("https://www.lottery.gov.cn/historykj/" + pagePath + ".jspx?_ltype=dlt").get();
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
        new CaiPiaoDaletou().updateRecord(1);
    }
}
