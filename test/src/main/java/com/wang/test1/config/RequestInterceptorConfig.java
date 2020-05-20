package com.wang.test1.config;

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


            private CharSequence route(RequestTemplate template) {
                ///template.
                // TODO 你的路由算法在这里
                /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();*/
                return "000";
            }
        };
    }

}
