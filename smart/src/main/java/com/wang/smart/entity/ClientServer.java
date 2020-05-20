package com.wang.smart.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "client_server")
public class ClientServer implements Serializable {
    @Id
    private Integer id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_num")
    private String clientNum;

    private static final long serialVersionUID = 1L;

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
     * @return client_name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNum() {
        return clientNum;
    }

    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }
}