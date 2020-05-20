package com.wang.gateway.service.impl;

import com.wang.core.common.ErrorCodes;
import com.wang.core.common.ResultCode;
import com.wang.gateway.dao.GatewayDefineMapper;
import com.wang.gateway.entity.GatewayDefine;
import com.wang.gateway.service.GatewayDefineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
public class GatewayDefineServiceImpl implements GatewayDefineService {
    @Autowired
    private GatewayDefineMapper gatewayDefineMapper;
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher publisher;

    @Override
    public String loadRouteDefinition() {
        try {
            List<GatewayDefine> gatewayDefineServiceAll = gatewayDefineMapper.selectAll();
            if (gatewayDefineServiceAll == null) {
                return "none route defined";
            }
            for (GatewayDefine gatewayDefine : gatewayDefineServiceAll) {
                RouteDefinition definition = new RouteDefinition();
                definition.setId(gatewayDefine.getId());
                definition.setUri(new URI(gatewayDefine.getUri()));
                List<PredicateDefinition> predicateDefinitions = gatewayDefine.getPredicateDefinition();
                if (predicateDefinitions != null) {
                    definition.setPredicates(predicateDefinitions);
                }
                List<FilterDefinition> filterDefinitions = gatewayDefine.getFilterDefinition();
                if (filterDefinitions != null) {
                    definition.setFilters(filterDefinitions);
                }
                routeDefinitionWriter.save(Mono.just(definition)).subscribe();
                this.publisher.publishEvent(new RefreshRoutesEvent(this));
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public ResultCode save(GatewayDefine gatewayDefine) {
        try {
            gatewayDefineMapper.insertSelective(gatewayDefine);
            RouteDefinition definition = new RouteDefinition();
            definition.setId(gatewayDefine.getId());
            definition.setUri(new URI(gatewayDefine.getUri()));
            List<PredicateDefinition> predicateDefinitions = gatewayDefine.getPredicateDefinition();
            if (predicateDefinitions != null) {
                definition.setPredicates(predicateDefinitions);
            }
            List<FilterDefinition> filterDefinitions = gatewayDefine.getFilterDefinition();
            if (filterDefinitions != null) {
                definition.setFilters(filterDefinitions);
            }
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return ResultCode.success(null);
        }catch (Exception e){
            return ResultCode.error(ErrorCodes.FAILED.getErrorNo(),"fail");
        }
    }

    @Override
    public void deleteById(String id)  {
        gatewayDefineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<GatewayDefine> selectAll() {
        return gatewayDefineMapper.selectAll();
    }

}
