package com.wang.smart.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("smart")
public class TestController {
    @RequestMapping("test")
    public String test(String str){
        return "smart-server is started!";
    }
}
