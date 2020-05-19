package com.wang.smart.config;

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
                if (url.contains("$CLUSTER_ID")) {
                    url = url.replace("$CLUSTER_ID", route(template));
                    template.uri(url);
                }
                if (url.startsWith("//")) {
                    url = "http:" + url;
                    template.target(url);
                    template.uri("");
                }

            }


            private CharSequence route(RequestTemplate template) {
                ///template.
                // TODO 你的路由算法在这里
                return "000";
            }
        };
    }

}
