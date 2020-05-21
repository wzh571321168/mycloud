package com.wang.smart.controller;

import com.wang.smart.common.RestResult;
import com.wang.smart.service.FaceFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("faceFeature")
public class FaceFeatureController {
    @Autowired
    private FaceFeatureService faceFeatureService;

    public RestResult add(String name,String pic){
        return faceFeatureService.add(name,pic);
    }
}
