package com.wang.test1.service;


import com.wang.test1.entity.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "smart-server")
public interface TestService {
    @RequestMapping(value = "smart-server/smart/test", method = RequestMethod.GET)
    String test(Balance balance);
}
