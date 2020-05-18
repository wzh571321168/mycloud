package com.wang.gateway.service;

import com.wang.core.common.ResultCode;
import com.wang.gateway.entity.GatewayDefine;

import java.util.List;

public interface GatewayDefineService {
    String loadRouteDefinition();
    ResultCode save(GatewayDefine gatewayDefine);
    void deleteById(String id);

    List<GatewayDefine> selectAll();
}
