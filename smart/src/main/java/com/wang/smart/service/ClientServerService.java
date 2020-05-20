package com.wang.smart.service;

import com.wang.core.common.ResultCode;
import com.wang.smart.dao.ClientServerMapper;
import com.wang.smart.entity.ClientServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServerService {
    @Autowired
    private ClientServerMapper clientServerMapper;

    public ResultCode list(){
        return ResultCode.success(clientServerMapper.selectAll());
    }

    public ResultCode add(ClientServer clientServer){
        clientServerMapper.insertSelective(clientServer);
        return ResultCode.success(null);
    }
    public ResultCode update(ClientServer clientServer){
        clientServerMapper.updateByPrimaryKeySelective(clientServer);
        return ResultCode.success(null);
    }
    public ResultCode delete(Integer id){
        clientServerMapper.deleteByPrimaryKey(id);
        return ResultCode.success(null);
    }
}
