package com.wang.smart.controller;

import com.github.pagehelper.Page;
import com.wang.core.common.ResultCode;
import com.wang.smart.common.ErrorCode;
import com.wang.smart.common.PageRequest;
import com.wang.smart.common.RestPageInfo;
import com.wang.smart.entity.ClientServer;
import com.wang.smart.service.ClientServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("clinetServer")
public class ClientServerController {
    @Autowired
    private ClientServerService clientServerService;
    @PostMapping("list")
    public RestPageInfo list(HttpServletRequest request){
        Page page = PageRequest.defaultPage(request);
        clientServerService.list();
        return RestPageInfo.success(page);

    }

    @PostMapping("add")
    public ResultCode add(ClientServer clientServer){
        return clientServerService.add(clientServer);
    }

    @PostMapping("update")
    public ResultCode update(ClientServer clientServer){
        return clientServerService.update(clientServer);

    }

    @PostMapping("delete")
    public ResultCode delete(Integer id){
        return clientServerService.delete(id);

    }



}
