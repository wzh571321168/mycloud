package com.wang.gateway.service;


import com.wang.gateway.common.RestResult;
import com.wang.gateway.entity.GatewayDefine;

import java.util.List;

public interface GatewayDefineService {
    String loadRouteDefinition();
    RestResult save(GatewayDefine gatewayDefine);
    void deleteById(String id);

    List<GatewayDefine> selectAll();
}
