package com.neeson.example.util;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrcHelper {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
//        System.out.println(Core.VERSION);
////        Imgproc.
//        long start = System.currentTimeMillis();
//        File imageFile = new File("D://bbb.jpg");
//        ITesseract instance = new Tesseract();  // JNA Interface Mapping
//        // ITesseract instance = new Tesseract1(); // JNA Direct Mapping
////         File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
////         instance.setDatapath(tessDataFolder.getParent());
////        instance.setLanguage("chi_sim");
//        instance.setLanguage("small");
//        try {
//            String result = instance.doOCR(imageFile);
//            System.out.println("used : "+(System.currentTimeMillis() -start) + result);
//
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }
//        for (int i = 1; i < 33; i++) {
//            test(i);
//        }


        /**
         * 4
         * 10
         * 13
         * 14
         * 15
         * 21
         * 24
         *
         * 1
         * 2
         * 19
         * 29
         */
        test(13);
        HighGui.waitKey();
    }


    private static void test(int index) {
//        ImageRe
        String fileName = "D://beifen2/a"+index+".png";
        Mat src = Imgcodecs.imread(fileName);
        Mat dst = new Mat();


//        // 3
        int beiShu = 3;
        int minHeight = 12;
        int midHeight = 24;
        int maxHeight1 = 28;
        int maxHeight2 = 36;
        int minWidth = 40;
        // 6
//        int beiShu = 3*2;
//        int minHeight = 12/2;
//        int midHeight = 22/2;
//        int maxHeight1 = 28/2;
//        int maxHeight2 = 36/2;
//        int minWidth = 40/2;

//        int beiShu = 4;
//        int minHeight = 7 ;
//        int midHeight = 17;
//        int maxHeight1 = 23;
//        int maxHeight2 = 26;
//        int minWidth = 16;

        Size mSize = new Size(src.width() / beiShu, src.height() / beiShu);
        Imgproc.resize(src, src, mSize);
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_BGRA2GRAY);
//        HighGui.imshow("显示图片1",dst.clone());
        Imgproc.threshold(dst, dst, 135, 255, Imgproc.THRESH_BINARY_INV);
//        HighGui.imshow("显示图片2",dst.clone());


        Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MARKER_CROSS, new Size(14, 1));
        Imgproc.erode(dst, dst, erodeElement);
//        erodeElement = Imgproc.getStructuringElement(Imgproc.MARKER_CROSS, new Size(14, 1));
//        Imgproc.erode(dst, dst, erodeElement);
        HighGui.imshow("显示图片2",dst.clone());
        List<MatOfPoint> contoursList = new ArrayList<>();
        Imgproc.findContours(dst, contoursList, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0, 0));
        Collections.sort(contoursList, new Comparator<MatOfPoint>() {
            @Override
            public int compare(MatOfPoint o1, MatOfPoint o2) {
                Rect rect1 = Imgproc.boundingRect(o1);
                Rect rect2 = Imgproc.boundingRect(o2);
                return rect1.y - rect2.y;
            }
        });
        List<Rect> rects = new ArrayList<>();
        for (int i = 0; i < contoursList.size(); i++) {
            Rect rect = Imgproc.boundingRect(contoursList.get(i));
            //排除无效区域
            if (rect.x > 2) {
                if ((rect.height > minHeight && rect.height < midHeight && rect.width > minWidth) || (rect.height > maxHeight1 && rect.height < maxHeight2)) {
                    System.out.println(rect.toString());
                    rects.add(rect);
                    if (true) {
                        Imgproc.rectangle(src, rect, new Scalar(0, 255, 0), 1, 8, 0);
                    }
                }
            }
        }
        HighGui.imshow(fileName, src.clone());

    }
}
