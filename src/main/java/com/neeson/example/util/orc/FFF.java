package com.neeson.example.util.orc;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.*;
import java.io.File;

public class FFF {

    public static void imgMatching2()  {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        String name = "yamen_choose";
//        Mat src_base = Imgcodecs.imread("D:\\"+name+".png");
//        Mat gray_base = new Mat();
//        // 转换为灰度
////        Imgproc.cvtColor(src_base, gray_base, Imgproc.COLOR_RGB2GRAY);//342  614
//        // D:\cut\half  // 288 335
//        Mat cut1 = new Mat(src_base,new Rect(54,328,288,335));
//        Mat cut2 = new Mat(src_base,new Rect(403,328,288,335));
//        Mat cut3 = new Mat(src_base,new Rect(755,328,288,335));
////        Mat cut1 = new Mat(src_base,new Rect(36,323,321,346));
////        Mat cut2 = new Mat(src_base,new Rect(385,323,321,346));
////        Mat cut3 = new Mat(src_base,new Rect(737,323,321,346));
//        // 210 30
//        Imgcodecs.imwrite("D:\\"+name+"1.jpg",cut1);
//        Imgcodecs.imwrite("D:\\"+name+"2.jpg",cut2);
//        Imgcodecs.imwrite("D:\\"+name+"3.jpg",cut3);
        // 288 335
       //229 266

        /**
         *    private String [] colors = {
         *         "#4D2210",//空
         *         "#363636",//白板
         *         "#344931",//绿色 男爵
         *         "#363636",
         *         "#C1861C", //黄1 #C2871C
         *         "#363636",
         *         "#E48A19",// 黄2 #E38818  #E58B1A
         *         "#363636"
         *
         *     };
         */
        int x = 186,y = 31;
        File file = new File("D:\\cut\\cardBg");
        File[] files = file.listFiles();
        int index = 0;
        for (File temp : files) {
            if (temp.isFile()){
                Mat tempMat = Imgcodecs.imread(temp.getAbsolutePath());
                if (index==5){
                    System.out.println(tempMat.rows() + "/"+tempMat.cols());
                    Mat clone = tempMat.clone();
                    clone.put(y,x, 0d,0d,255d);
                    Imgcodecs.imwrite("D:\\clone1.jpg",clone);
                }
                index++;
                // 30 250
                double[] doubles = tempMat.get(y, x);
                if (doubles!=null){
                    Color color = new Color((int) doubles[2], (int) doubles[1], (int) doubles[0], 255);
                    String hexString = Integer.toHexString(color(color));
                    System.out.println(temp.getName() + " : #"+  hexString);
                }

            }
        }
    }

    private static int color(Color color){
        return (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }


    public static void main(String[] args) {
        imgMatching2();
    }
}
