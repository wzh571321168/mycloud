package com.wang.smart.controller.streemserver;

import com.wang.smart.service.RtspService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//http://127.0.0.1:5555/camera_monitor/on_publish;
@RestController
@RequestMapping("camera_monitor")
public class StreamCallbackController {
    @Autowired
    private RtspService rtspService;
    @RequestMapping("on_publish")
    public void onPublish(){
        System.out.printf("on_publish");
    }
    @RequestMapping("on_publish_done")
    public void onPublishDone(){
        System.out.printf("on_publish_done");
    }

    /**
     * 播放回调
     * @param server
     * @param uid
     * @return
     */
    @RequestMapping("on_play")
    public void onPlay(@RequestParam("server") String server,@RequestParam("uid") String uid){
        if(StringUtils.isNotBlank(server)&&StringUtils.isNoneBlank(uid)){
            rtspService.push(server,uid);
        }
    }
}
