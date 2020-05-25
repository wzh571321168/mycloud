package com.wang.smart.service;


import com.wang.smart.common.RestResult;
import com.wang.smart.dao.ClientServerMapper;
import com.wang.smart.entity.ClientServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServerService {
    @Autowired
    private ClientServerMapper clientServerMapper;

    public RestResult list(){
        return RestResult.success(clientServerMapper.selectAll());
    }

    public RestResult add(ClientServer clientServer){
        clientServerMapper.insertSelective(clientServer);
        return RestResult.VOID_SUCCESS_RESULT;
    }
    public RestResult update(ClientServer clientServer){
        clientServerMapper.updateByPrimaryKeySelective(clientServer);
        return RestResult.VOID_SUCCESS_RESULT;
    }
    public RestResult delete(Integer id){
        clientServerMapper.deleteByPrimaryKey(id);
        return RestResult.VOID_SUCCESS_RESULT;
    }
}
