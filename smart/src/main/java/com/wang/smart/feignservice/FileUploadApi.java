package com.wang.smart.feignservice;

import com.wang.core.common.ResultCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(
        name = "smart-service"
)
@RequestMapping("file")
public interface FileUploadApi {
    @PostMapping("uploadFile")
    ResultCode<String> uploadFile(@RequestPart("file") MultipartFile file);
    @PostMapping("uploadFiles")
    ResultCode<List<String>> uploadFiles(@RequestPart("file") MultipartFile[] files);
    @PostMapping("upload")
    ResultCode<String> upload(String file);
}
