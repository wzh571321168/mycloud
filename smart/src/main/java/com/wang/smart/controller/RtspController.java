package com.wang.smart.controller;

import cn.hutool.core.lang.UUID;
import com.github.pagehelper.Page;
import com.wang.smart.common.PageRequest;
import com.wang.smart.common.RestPageInfo;
import com.wang.smart.common.RestResult;
import com.wang.smart.entity.RtspAddress;
import com.wang.smart.service.RtspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("rtspAddress")
public class RtspController {
    @Autowired
    private RtspService rtspService;
    @GetMapping("list")
    public RestPageInfo list(HttpServletRequest request){
        Page page = PageRequest.defaultPage(request);
        rtspService.list();
        return RestPageInfo.success(page);
    }
    @PostMapping("add")
    public RestResult add(RtspAddress rtspAddress){
        rtspAddress.setUid(UUID.randomUUID().toString());
        rtspService.add(rtspAddress);
        return RestResult.VOID_SUCCESS_RESULT;
    }
    @PostMapping("edit")
    public RestResult edit(RtspAddress rtspAddress){
        rtspService.edit(rtspAddress);
        return RestResult.VOID_SUCCESS_RESULT;
    }
    @PostMapping("delete")
    public RestResult delete(Integer id){
        rtspService.delete(id);
        return RestResult.VOID_SUCCESS_RESULT;
    }

}
