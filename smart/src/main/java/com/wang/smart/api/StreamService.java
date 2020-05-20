package com.wang.smart.api;

import com.wang.smart.common.RestResult;
import com.wang.smart.entity.MonitorVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="analyze-client", qualifier = "streamService")
@RequestMapping("analyze-client-$clientId/stream")
public interface StreamService {
    @PostMapping(value = "push",consumes = "application/json")
    RestResult push( MonitorVo monitorVo);
    @PostMapping("pushDone")
    RestResult pushDone( MonitorVo monitorVo);
}
