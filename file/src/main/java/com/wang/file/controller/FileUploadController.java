package com.wang.file.controller;

import com.wang.core.common.ResultCode;
import com.wang.file.oss.OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("uploadFile")
    public ResultCode<String> uploadFile(@RequestPart("file") MultipartFile file){
        String s = OSSUtil.uploadFile(file);
        return ResultCode.success(s);
    }
    @PostMapping("uploadFiles")
    public ResultCode<List<String>> uploadFiles(@RequestPart("file") MultipartFile[] files){
        List<String> strings = OSSUtil.uploadFile(files, "");
        return ResultCode.success(strings);
    }
    @PostMapping("upload")
    public ResultCode<String> upload(String file){
        String s = OSSUtil.upload(String.valueOf(System.currentTimeMillis()),file);
        return ResultCode.success(s);
    }
}
