package com.wang.smart.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "rtsp_address")
public class RtspAddress implements Serializable {
    @Id
    private Integer id;

    @Column(name = "rtsp_address")
    private String rtspAddress;

    private String name;

    private String uid;

    private String status;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "analyze_type")
    private String analyzeType;

    public String getAnalyzeType() {
        return analyzeType;
    }

    public void setAnalyzeType(String analyzeType) {
        this.analyzeType = analyzeType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    private static final long serialVersionUID = 1L;



    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return rtsp_address
     */
    public String getRtspAddress() {
        return rtspAddress;
    }

    /**
     * @param rtspAddress
     */
    public void setRtspAddress(String rtspAddress) {
        this.rtspAddress = rtspAddress;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}