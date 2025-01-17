package com.wang.smart.config;

import com.alibaba.fastjson.JSONObject;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RequestInterceptorConfig {
    @Bean
    public RequestInterceptor cloudContextInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                String url = template.url();
                if (url.contains("$clientId")) {
                    url = url.replace("$clientId", route(template));
                    template.uri(url);
                }
                if (url.startsWith("//")) {
                    url = "http:" + url;
                    template.target(url);
                    template.uri("");
                }

            }


            //路由
            private CharSequence route(RequestTemplate template) {
                String s = new String(template.body());
                JSONObject jsonObject = JSONObject.parseObject(s);
                return jsonObject.get("clientNum").toString();
            }
        };
    }

}
