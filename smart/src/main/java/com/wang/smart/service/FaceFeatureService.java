package com.wang.smart.service;

import com.wang.smart.common.RestResult;
import com.wang.smart.dao.FaceFeatureMapper;
import com.wang.smart.entity.FaceFeature;

import com.wang.smart.utils.Base64Utils;
import com.wang.smart.utils.FaceFeatureUtil;
import com.wang.smart.utils.FaceUtils;
import com.wang.smart.utils.MatUtils;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_face.*;

import org.bytedeco.javacpp.opencv_imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.IntBuffer;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;

@Service
public class FaceFeatureService {
    @Value("${file.path}")
    private String filePath;
    @Autowired
    private FaceFeatureUtil faceUtils;
    @Autowired
    private FaceFeatureMapper faceFeatureMapper;

    //private static String path=Class.class.getClass().getResource("/").getPath().substring(1);
    private static FaceRecognizer fr= FisherFaceRecognizer.create();

    /*static {
        path=Class.class.getClass().getResource("/").getPath().substring(1);
        File file=new File(path+"facefeature/faceRecord.xml");
        if(file.exists()){
            fr.read(path+"facefeature/faceRecord.xml");
            fr.setThreshold(3000.0);
        }
    }*/

    public Integer insert(String name,String path){
        FaceFeature faceFeature=new FaceFeature();
        faceFeature.setName(name);
        faceFeature.setPath(path);
        faceFeatureMapper.insertSelective(faceFeature);
        return faceFeature.getId();
    }

    public synchronized RestResult add(String name, String pic) {
        String picPath=filePath+name+"-"+System.currentTimeMillis()+".jpg";
        Base64Utils.generateImage2(pic,picPath);
        Integer insert = insert(name, picPath);
        List<FaceFeature> faceFeatures = faceFeatureMapper.selectAll();
        if(faceFeatures!=null&&faceFeatures.size()>=2){
            MatVector matVector=new MatVector(faceFeatures.size());
            Mat lables = new Mat(faceFeatures.size(),1,CV_32SC1);//对应20个标签值
            IntBuffer lablesBuf = lables.createBuffer();
            //System.out.println(faceFeatures.size());
            for(int i=0;i<faceFeatures.size();i++){
                lablesBuf.put(i,faceFeatures.get(i).getId());
               // System.out.println(i);
            }
            for(int i=0;i<faceFeatures.size();i++){
                Mat imread=null;
                try {
                    imread = MatUtils.imagePath2Mat(faceFeatures.get(i).getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<Mat> matList = faceUtils.detectFace(imread);
                matVector.put(i,matList.get(0));
            }
            //训练
            fr.train(matVector,lables);
            //保存训练结果
            fr.save("smart/src/main/resources/facefeature/FisherRecognize.xml");

            //读取训练出的xml文件
            fr.read("smart/src/main/resources/facefeature/FisherRecognize.xml");
            //设置阈值，阈值为0则任何人都不认识，阈值特别大的时候任何人都认识（返回和样本最相似的结果，永远不会返回-1）
            //前面忘记说了，检测返回-1代表不能和训练结果匹配
            fr.setThreshold(3000.0);
        }
       /* try {
            Mat mat = MatUtils.base642Mat(pic);
            List<Mat> matList = faceUtils.detectFace(mat);
            if(matList!=null&&matList.size()==1){

                if(faceFeatures!=null&&faceFeatures.size()>0){

                }else {
                    String path=filePath+name+"-"+System.currentTimeMillis()+".jpg";
                    if(Base64Utils.generateImage2(pic,path)) {
                        Integer id = insert(name, path);
                        MatVector images =new MatVector(2);//一共20个训练样本
                        Mat lables = new Mat(2,1,CV_32SC1);//对应20个标签值
                        //写入标签值，前十个为1，后十个为2
                        IntBuffer lablesBuf = lables.createBuffer();
                        lablesBuf.put(0, 0);
                        lablesBuf.put(1, id);
                        images.put(0,matList.get(0));
                        images.put(1,matList.get(0));

                    }
                }
            }else {
                throw new RuntimeException("该图片未检测出人脸或人脸数大于1");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return RestResult.VOID_SUCCESS_RESULT;
    }

    ////创建人脸分类器，有Fisher、Eigen、LBPH，选哪种自己决定，这里使用FisherFaceRecognizer
    //		FaceRecognizer fr = FisherFaceRecognizer.create();
    //		//训练
    //		fr.train(images, lables);
    //		//保存训练结果
    //		fr.save("FisherRecognize.xml");
    //
    //		//读取训练出的xml文件
    //		fr.read("FisherRecognize.xml");
    //		//设置阈值，阈值为0则任何人都不认识，阈值特别大的时候任何人都认识（返回和样本最相似的结果，永远不会返回-1）
    //		//前面忘记说了，检测返回-1代表不能和训练结果匹配
    //		fr.setThreshold(3000.0);
    //————————————————


}
