package com.wang.smart.controller;


import com.wang.smart.api.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
@RequestMapping("smart")
public class TestController {
    @Autowired
    private TestService testService;
    @RequestMapping("test")
    public String test(String str){
        return "smart-server is started!";
    }
    @RequestMapping("smartTest")
    public String smartTest(){
        return testService.test();
    }

}
