package com.wang.file.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OssConfig {
    /**
     * 阿里云应用ID
     */
    public static String accessId;

    /**
     * 阿里云API密钥
     */
    public static String accessKey;

    /**
     * OSS域名
     */
    public static String endpoint;

    /**
     * OSS磁盘
     */
    public static String bucket;

    public String getAccessId() {
        return accessId;
    }

    @Value("${oss.accessId}")
    public void setAccessId(String accessId) {
        OssConfig.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    @Value("${oss.accessKey}")
    public void setAccessKey(String accessKey) {
        OssConfig.accessKey = accessKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Value("${oss.endpoint}")
    public void setEndpoint(String endpoint) {
        OssConfig.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    @Value("${oss.bucket}")
    public void setBucket(String bucket) {
        OssConfig.bucket = bucket;
    }
}
