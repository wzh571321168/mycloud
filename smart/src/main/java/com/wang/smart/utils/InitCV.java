package com.wang.smart.utils;

import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitCV {
    public  final String BASE64="BASE64";
    public  final String URL="URL";
    // 初始化人脸探测器
    CascadeClassifier faceDetector;

    CascadeClassifier eyeDetector;

    @PostConstruct
    public void init(){
        String path=Class.class.getClass().getResource("/").getPath();
        System.load(path+"cv\\opencv_java412.dll");
        faceDetector = new CascadeClassifier(
                path+"ocv\\haarcascade_frontalface_alt.xml");

        eyeDetector = new CascadeClassifier(path+"opencv\\haarcascade_eye.xml");
    }
}
