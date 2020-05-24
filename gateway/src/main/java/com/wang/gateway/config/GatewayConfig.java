package com.wang.gateway.config;

import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GatewayConfig {
    @Bean
    public RouteDefinitionWriter routeDefinitionWriter() {
        return new InMemoryRouteDefinitionRepository();
    }

    /*@Bean
    public MysqlRouteDefinitionRepository mysqlRouteDefinitionRepository() {
        return new MysqlRouteDefinitionRepository();
    }*/
}
