package com.wang.smart.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="payment-service", qualifier = "testService")
@RequestMapping("test-server")
public interface TestService {
    @GetMapping(value = "test")
    String test();
}
