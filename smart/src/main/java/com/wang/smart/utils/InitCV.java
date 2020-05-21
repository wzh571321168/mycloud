package com.wang.smart.utils;


import org.bytedeco.javacpp.opencv_objdetect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitCV {
    public  final String BASE64="BASE64";
    public  final String URL="URL";
    // 初始化人脸探测器opencv_objdetect.CascadeClassifier face_cascade = new opencv_objdetect.CascadeClassifier(
    opencv_objdetect.CascadeClassifier faceDetector;

    opencv_objdetect.CascadeClassifier eyeDetector;

    @PostConstruct
    public void init(){
        String path=Class.class.getClass().getResource("/").getPath();
        System.load(path+"cv\\opencv_java412.dll");
        faceDetector = new opencv_objdetect.CascadeClassifier(path.substring(1,path.length())+"cv\\haarcascade_frontalface_alt.xml");
        //System.out.println(path.substring(1,path.length())+"cv\\haarcascade_frontalface_alt.xml");
        /*faceDetector = new opencv_objdetect.CascadeClassifier(
                "E:/java/work/mycloud/smart/target/classes/cv\\haarcascade_frontalface_alt.xml");*/

        eyeDetector = new opencv_objdetect.CascadeClassifier(path.substring(1,path.length())+"cv\\haarcascade_eye.xml");
    }
}
