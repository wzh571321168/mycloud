package com.wang.smart.feignservice;

import com.wang.smart.common.RestResult;
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
    RestResult<String> uploadFile(@RequestPart("file") MultipartFile file);
    @PostMapping("uploadFiles")
    RestResult<List<String>> uploadFiles(@RequestPart("file") MultipartFile[] files);
    @PostMapping("upload")
    RestResult<String> upload(String file);
}
