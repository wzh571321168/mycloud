package com.wang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.wang.gateway"})
@MapperScan(basePackages = {"com.wang.gateway.dao"})
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = {"com.wang.**.api"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
