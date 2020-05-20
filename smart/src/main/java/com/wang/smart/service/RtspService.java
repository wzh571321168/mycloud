package com.wang.smart.service;

import cn.hutool.http.HttpUtil;
import com.wang.smart.dao.RtspAddressMapper;
import com.wang.smart.entity.RtspAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RtspService  {
    @Autowired
    private RtspAddressMapper rtspAddressMapper;

    public void push(String server, String uid) {
        //查询数据库是否存在该rtsp地址，如果存在，调用推流客户端服务，不存在，不处理
        RtspAddress rtspAddress=new RtspAddress();
        rtspAddress.setStatus("Y");
        rtspAddress.setUid(uid);
        rtspAddress = rtspAddressMapper.selectOne(rtspAddress);
        if(rtspAddress!=null){
            Map<String,Object> map=new HashMap<>();
            map.put("rtspUrl",rtspAddress.getRtspAddress());
            String post = HttpUtil.post("localhost:8084/" + rtspAddress.getServerName() + "/push", map);
        }

    }

    public void list() {
        rtspAddressMapper.selectAll();
    }

    public void add(RtspAddress rtspAddress) {
        rtspAddressMapper.insertSelective(rtspAddress);
    }

    public void edit(RtspAddress rtspAddress) {
        rtspAddressMapper.updateByPrimaryKeySelective(rtspAddress);
    }

    public void delete(Integer id) {
        rtspAddressMapper.deleteByPrimaryKey(id);
    }
}
