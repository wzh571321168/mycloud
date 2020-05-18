package com.wang.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@ServletComponentScan
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.wang.smart"})
@MapperScan(basePackages = {"com.wang.smart.dao"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.wang.**.api"})
//@EnableSwagger2
public class SmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartApplication.class, args);
    }

}
