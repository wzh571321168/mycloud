package com.wang.smart.utils;

import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_imgcodecs.imread;
import static org.bytedeco.javacpp.opencv_imgcodecs.imwrite;

@Component
public class FaceFeatureUtil {
    /**
     * 把人像图片的人脸识别并裁剪出来，保存到同目录
     * @throws Exception
     * @throws InterruptedException
     */
    public  List<Mat> detectFace(Mat image)
    {
        //Mat image = imread(path);
        if (image.empty()) {
            throw new RuntimeException("加载图片出错，请检查图片路径！");

        }
//        imshow("显示原始图像", image);

        RectVector faces = new RectVector();

        //加载检测器
        opencv_objdetect.CascadeClassifier face_cascade = new opencv_objdetect.CascadeClassifier("D:\\soft\\openCV3\\opencv\\sources\\data\\haarcascades_cuda\\haarcascade_frontalface_alt.xml");//初始化人脸检测器

        //当前帧图片进行灰度+直方均衡
       Mat videoMatGray = new Mat();
        opencv_imgproc.cvtColor(image, videoMatGray, Imgproc.COLOR_BGRA2GRAY);
        opencv_imgproc.equalizeHist(videoMatGray, videoMatGray);

        //使用检测器进行检测，把结果放进集合中
        face_cascade.detectMultiScale(videoMatGray, faces);

        //把所有人脸数据绘制到图片中
       /* File imagePath = new File(path);
        String dir = imagePath.getParent();
        String name = imagePath.getName().substring(0, imagePath.getName().lastIndexOf("."));*/
        List<Mat> matList=new ArrayList<>();
        for (int i = 0; i < faces.size(); i++) {
            Rect face = faces.get(i);
            Mat img_region = new Mat(image, face);
            matList.add(img_region);
//            imshow("人脸裁剪"+i, img_region);
            //imwrite(dir+File.separator+name+" face"+i+".png", img_region);
        }
        return matList;
//        waitKey(0);
    }
}
