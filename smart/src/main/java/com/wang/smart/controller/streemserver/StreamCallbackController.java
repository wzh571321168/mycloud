package com.wang.smart.controller.streemserver;

import com.wang.smart.service.RtspService;
import org.apache.commons.lang3.StringUtils;
import org.bytedeco.javacpp.opencv_core;
import org.opencv.face.EigenFaceRecognizer;
import org.opencv.face.FaceRecognizer;
import org.opencv.face.FisherFaceRecognizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.Buffer;
import java.nio.IntBuffer;

import static org.bytedeco.javacpp.opencv_core.CV_32SC1;

//http://127.0.0.1:5555/camera_monitor/on_publish;
@RestController
@RequestMapping("camera_monitor")
public class StreamCallbackController {
    @Autowired
    private RtspService rtspService;
    @RequestMapping("on_connect")
    public String on_connect(HttpServletRequest request){
        System.out.printf("on_connect");
        return "on_connect";
    }
    @GetMapping("on_publish")
    public String onPublish(HttpServletRequest request){
        System.out.printf("on_publish");

        return "on_publish";
    }
    @GetMapping("on_publish_done")
    public String onPublishDone(@RequestParam("uid") String uid){
        System.out.printf("on_publish_done");
        return "on_publish_done";
    }

    /**
     * 播放回调
     * @param uid
     * @return
     */
    @GetMapping("on_play")
    public String onPlay(@RequestParam("uid") String uid){

        System.out.printf("on_play");
        if(StringUtils.isNoneBlank(uid)){
            rtspService.push(uid,"do");
            //EigenFaceRecognizer eigenFaceRecognizer = EigenFaceRecognizer.create();

        }
        return "on_play";
    }

    /**
     * 播放关闭回调
     * @param uid
     */
    @GetMapping("on_play_done")
    public String onPlayDone(@RequestParam("uid") String uid){
        System.out.printf("on_play_done");
        if(StringUtils.isNoneBlank(uid)){
            rtspService.push(uid,"done");
        }
        return "on_play_done";
    }
}
