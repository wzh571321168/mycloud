package com.wang.smart.service;

import com.wang.smart.dao.ClientServerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServerService {
    @Autowired
    private ClientServerMapper clientServerMapper;
}
