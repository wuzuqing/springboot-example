package com.neeson.example.util.orc;

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.opencv.core.Core.NORM_HAMMING;

public class FFF {

    public static void imgMatching2() {
        int index = 2;
//        Mat base_src = Imgcodecs.imread("D:\\base_" + index + ".png");
        Mat base_src = Imgcodecs.imread("D:\\" + index + ".png");
        Imgproc.cvtColor(base_src, base_src, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.

        Mat dst = new Mat();
        Core.inRange(base_src, new Scalar(78, 43, 46), new Scalar(110, 255, 255), dst);
        int ratio = 6;
        double defIndex0 = 76;
        double defIndex1 = 112;
        double defIndex2 = 71;
//        Imgproc.resize(base_src,base_src,new Size(base_src.width()/ratio,base_src.height()/ratio));
//        int cols = base_src.cols();
//        int rows = base_src.rows();
//
//        long start = System.currentTimeMillis();
//        int count = 0;
//        for (int i = 0; i < cols; i++) {
//            for (int j = 0; j < rows; j++) {
//                double[] doubles = base_src.get(j, i);
//                if (doubles[0] != defIndex0 && doubles[1] != defIndex1 && doubles[2] != defIndex2) {
//                    System.out.println("row:" + j + " col:" + i + " value:" + Arrays.toString(doubles));
//                    count++;
//                    base_src.put(j,i,0,0,255);
//                    break;
//                }
//            }
//        }
//        System.out.println("used:"+(System.currentTimeMillis()-start) + " count:"+count );
        HighGui.imshow("22", dst);
        HighGui.waitKey();
    }


    private static void test() {
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
        int x = 186, y = 31;
        File file = new File("D:\\cut\\cardBg");
        File[] files = file.listFiles();
        int index = 0;
        for (File temp : files) {
            if (temp.isFile()) {
                Mat tempMat = Imgcodecs.imread(temp.getAbsolutePath());
                if (index == 5) {
                    System.out.println(tempMat.rows() + "/" + tempMat.cols());
                    Mat clone = tempMat.clone();
                    clone.put(y, x, 0d, 0d, 255d);
                    Imgcodecs.imwrite("D:\\clone1.jpg", clone);
                }
                index++;
                // 30 250
                double[] doubles = tempMat.get(y, x);
                if (doubles != null) {
                    Color color = new Color((int) doubles[2], (int) doubles[1], (int) doubles[0], 255);
                    String hexString = Integer.toHexString(color(color));
                    System.out.println(temp.getName() + " : #" + hexString);
                }

            }
        }
    }

    private static int color(Color color) {
        return (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        imgMatching2();
//        test3();
        suanFaFAST();
    }

    private static void test3() {
        // D:\cut\1579071256455_403\对比
        File dir = new File("D:\\cut\\1579074412603_403\\test");
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        // left+(288-tempW)/2, top-tempW-10+335
        int totalValue;
        int index = 0;
        File one = files[0];
        File two = files[1];
        File three = files[2];

        printValue(one,1);
        printValue(two,3);
        //不动
        printValue(three,0);
    }

    private static void printValue(File file, int col) {
        Mat mat = Imgcodecs.imread(file.getAbsolutePath());
        if (mat.height() < 100) {
            return;
        }

        mat = new Mat(mat, new Rect((mat.width() - 100) / 2, mat.height() - 80 - 10, 100, 80));
        int rows = mat.rows();
        int totalValue = 0;
        for (int i = 0; i < rows; i++) {
            double[] doubles = mat.get(i, col);
            totalValue += (doubles[0] + doubles[1] + doubles[2]);
        }
        System.out.println(file.getName() + " " + totalValue + " " + Arrays.toString(mat.get(0, col)));
    }

    private static void suanFaORB(){
        //Keypoint detection的演算法有很多，openCV便提供了十一种方法：
        //
        //“FAST" – FastFeatureDetector
        //“STAR" – StarFeatureDetector
        //“SIFT" – SIFT (nonfree module)
        //“SURF" – SURF (nonfree module)
        //“ORB" – ORB
        //“BRISK" – BRISK
        //“MSER" – MSER
        //“GFTT" – GoodFeaturesToTrackDetector
        //“HARRIS" – GoodFeaturesToTrackDetector with Harris detector enabled
        //“Dense" – DenseFeatureDetector
        //“SimpleBlob" – SimpleBlobDetector
        //         还有以下二种Grid和Pyramid的方式，可合并以上各方法来使用：
        //“Grid" – GridAdaptedFeatureDetector
        //“Pyramid" – PyramidAdaptedFeatureDetector  
        //————————————————
        //detector = cv2.FeatureDetector_create(“FAST")
        //
        //kps = detector.detect(gray)
        //
        //print(“# of keypoints: {}".format(len(kps)))
        // D:\cut\1579074412603_403\test\1.png
//        Mat mat = Imgcodecs.imread("D:\\cut\\half\\23.png");
//
        Mat mat = Imgcodecs.imread("D:\\cut\\1579074412603_403\\test\\1.png");

//        mat = new Mat(mat,new Rect(36,45,mat.width()-36*2,mat.height()-55));
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY);
        ORB detector = ORB.create();
        MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();
        detector.detectAndCompute(mat,new Mat(),matOfKeyPoint,mat);

        Mat dst = new Mat();
        Features2d.drawKeypoints(mat,matOfKeyPoint,dst);
//
        mat = Imgcodecs.imread("D:\\cut\\half\\23.png");
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY);
        MatOfKeyPoint matOfKeyPoint1 = new MatOfKeyPoint();
        Mat dst1 = new Mat();

        detector.detectAndCompute(mat,new Mat(),matOfKeyPoint1,mat);
//        Features2d.drawKeypoints(mat,matOfKeyPoint1,dst1);


        BFMatcher matcher = BFMatcher.create(NORM_HAMMING);
        List<MatOfDMatch> matches = new ArrayList<>();
        matcher.knnMatch(dst,dst1,matches,2);

        List<DMatch> result = new ArrayList<>();
        for (MatOfDMatch match : matches) {
//            List<DMatch> matches = match.toList();
//            for (DMatch dMatch : matches) {
//                if (dMatch.distance>)
//            }
        }

//        List<KeyPoint> keyPoints = matOfKeyPoint.toList();
//
//
//        List<MatOfPoint> nameContours = new ArrayList<>();
//
//        for (KeyPoint point : keyPoints) {
//            nameContours.add(new MatOfPoint(point.pt));
//        }
//
//        Imgproc.drawContours(mat, nameContours, 0, new Scalar( 255, 100, 20), -1);
        MatOfDMatch matOfDMatch = new MatOfDMatch();
        Mat out = new Mat();
        Features2d.drawMatches(dst,matOfKeyPoint,dst1,matOfKeyPoint,matOfDMatch,out);


        System.out.println(matOfKeyPoint.toList().size());
        HighGui.imshow("point",dst);
        HighGui.imshow("point1",dst1);
        HighGui.imshow("out",out);
        HighGui.waitKey();
    }
    private static void suanFaFAST(){
        //Keypoint detection的演算法有很多，openCV便提供了十一种方法：
        //
        //“FAST" – FastFeatureDetector
        //“STAR" – StarFeatureDetector
        //“SIFT" – SIFT (nonfree module)
        //“SURF" – SURF (nonfree module)
        //“ORB" – ORB
        //“BRISK" – BRISK
        //“MSER" – MSER
        //“GFTT" – GoodFeaturesToTrackDetector
        //“HARRIS" – GoodFeaturesToTrackDetector with Harris detector enabled
        //“Dense" – DenseFeatureDetector
        //“SimpleBlob" – SimpleBlobDetector
        //         还有以下二种Grid和Pyramid的方式，可合并以上各方法来使用：
        //“Grid" – GridAdaptedFeatureDetector
        //“Pyramid" – PyramidAdaptedFeatureDetector  
        //————————————————
        //detector = cv2.FeatureDetector_create(“FAST")
        //
        //kps = detector.detect(gray)
        //
        //print(“# of keypoints: {}".format(len(kps)))
        // D:\cut\1579074412603_403\test\1.png
//        Mat mat = Imgcodecs.imread("D:\\cut\\half\\23.png");
//
        Mat mat = Imgcodecs.imread("D:\\cut\\1579074412603_403\\test\\1.png");

//        mat = new Mat(mat,new Rect(36,45,mat.width()-36*2,mat.height()-55));
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY);
        FastFeatureDetector detector = FastFeatureDetector.create();
        MatOfKeyPoint matOfKeyPoint = new MatOfKeyPoint();
        detector.detect(mat,matOfKeyPoint);
        Mat dst = new Mat();
        Features2d.drawKeypoints(mat,matOfKeyPoint,dst);
//
         mat = Imgcodecs.imread("D:\\cut\\half\\23.png");
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_BGR2GRAY);
        MatOfKeyPoint matOfKeyPoint1 = new MatOfKeyPoint();
        Mat dst1 = new Mat();

        detector.detect(mat,matOfKeyPoint1);
        Features2d.drawKeypoints(mat,matOfKeyPoint1,dst1);
//        List<KeyPoint> keyPoints = matOfKeyPoint.toList();
//
//
//        List<MatOfPoint> nameContours = new ArrayList<>();
//
//        for (KeyPoint point : keyPoints) {
//            nameContours.add(new MatOfPoint(point.pt));
//        }
//
//        Imgproc.drawContours(mat, nameContours, 0, new Scalar( 255, 100, 20), -1);
        MatOfDMatch matOfDMatch = new MatOfDMatch();
        Mat out = new Mat();
        Features2d.drawMatches(dst,matOfKeyPoint,dst1,matOfKeyPoint,matOfDMatch,out);


        System.out.println(matOfKeyPoint.toList().size());
        HighGui.imshow("point",dst);
        HighGui.imshow("point1",dst1);
        HighGui.imshow("out",out);
        HighGui.waitKey();
    }
}
