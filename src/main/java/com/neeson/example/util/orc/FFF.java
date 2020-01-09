//package com.neeson.example.util.orc;
//
//import org.opencv.core.*;
//import org.opencv.features2d.DescriptorMatcher;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.features2d.*;
//public class FFF {
//
//    public void imgMatching2() throws Exception {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat src_base = Imgcodecs.imread("D:\\test\\test5.jpg");
//        Mat src_test = Imgcodecs.imread("D:\\test\\test3.jpg");
//        Mat gray_base = new Mat();
//        Mat gray_test = new Mat();
//        // 转换为灰度
//        Imgproc.cvtColor(src_base, gray_base, Imgproc.COLOR_RGB2GRAY);
//        Imgproc.cvtColor(src_test, gray_test, Imgproc.COLOR_RGB2GRAY);
//        // 初始化ORB检测描述子
//        FeatureDetector featureDetector = FastFeatureDetector.create(FastFeatureDetector.ORB);//特别提示下这里opencv暂时不支持SIFT、SURF检测方法，这个好像是opencv(windows) java版的一个bug,本人在这里被坑了好久。
//        DescriptorExtractor descriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
//        // 关键点及特征描述矩阵声明
//        MatOfKeyPoint keyPoint1 = new MatOfKeyPoint(), keyPoint2 = new MatOfKeyPoint();
//        Mat descriptorMat1 = new Mat(), descriptorMat2 = new Mat();
//        // 计算ORB特征关键点
//        featureDetector.detect(gray_base, keyPoint1);
//        featureDetector.detect(gray_test, keyPoint2);
//        // 计算ORB特征描述矩阵
//        descriptorExtractor.compute(gray_base, keyPoint1, descriptorMat1);
//        descriptorExtractor.compute(gray_test, keyPoint2, descriptorMat2);
//        float result = 0;
//        // 特征点匹配
//        System.out.println("test5：" + keyPoint1.size());
//        System.out.println("test3：" + keyPoint2.size());
//        if (!keyPoint1.size().empty() && !keyPoint2.size().empty()) {
//            // FlannBasedMatcher matcher = new FlannBasedMatcher();
//            DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_L1);
//            MatOfDMatch matches = new MatOfDMatch();
//            matcher.match(descriptorMat1, descriptorMat2, matches);
//            // 最优匹配判断
//            double minDist = 100;
//            DMatch[] dMatchs = matches.toArray();
//            int num = 0;
//            for (int i = 0; i < dMatchs.length; i++) {
//                if (dMatchs[i].distance <= 2 * minDist) {
//                    result += dMatchs[i].distance * dMatchs[i].distance;
//                    num++;
//                }
//            }
//            // 匹配度计算
//            result /= num;
//        }
//        System.out.println(result);
//    }
//}
