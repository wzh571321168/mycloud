package com.wang.smart.controller;

import com.wang.core.common.ErrorCodes;
import com.wang.core.common.ResultCode;
import com.wang.smart.service.FaceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/smart/face")
public class FaceController {
    @Autowired
    private FaceService faceService;

    /**
     *  两个人脸图片比对
     * @param faceA 人脸A
     * @param faceB 人脸B
     * @param imageType 图片类型，BYSE64或URL
     * @return
     */
    @PostMapping("/compare")
    public ResultCode compare(String faceA,String faceB,String imageType){
        if(StringUtils.isBlank(faceA)||StringUtils.isBlank(faceB)||StringUtils.isBlank(imageType)){
            return new ResultCode(ErrorCodes.PARAM_NOT_ERROR);
        }
        return faceService.compare(faceA,faceB,imageType);
    }
    @PostMapping("/addFaceLib")
    public ResultCode addFaceLib(String face){
        if(StringUtils.isBlank(face)){
            return new ResultCode(ErrorCodes.PARAM_NOT_ERROR);
        }
        return faceService.addFaceLib(face);
    }
}
