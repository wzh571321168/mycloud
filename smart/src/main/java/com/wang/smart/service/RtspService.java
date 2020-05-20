package com.wang.smart.service;

import cn.hutool.http.HttpUtil;
import com.wang.smart.api.StreamService;
import com.wang.smart.dao.ClientServerMapper;
import com.wang.smart.dao.RtspAddressMapper;
import com.wang.smart.entity.MonitorVo;
import com.wang.smart.entity.RtspAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RtspService  {
    @Autowired
    private RtspAddressMapper rtspAddressMapper;
    @Autowired
    private ClientServerMapper clientServerMapper;
    @Autowired
    private StreamService streamService;

    public void push( String uid,String type) {
        //查询数据库是否存在该rtsp地址，如果存在，调用推流客户端服务，不存在，不处理
        RtspAddress rtspAddress=new RtspAddress();
        rtspAddress.setStatus("Y");
        rtspAddress.setUid(uid);
        rtspAddress = rtspAddressMapper.selectOne(rtspAddress);
        if(rtspAddress!=null){
            Map<String,Object> map=new HashMap<>();
            map.put("rtspUrl",rtspAddress.getRtspAddress());
            MonitorVo monitorVo=new MonitorVo();
            monitorVo.setAddress(rtspAddress.getRtspAddress());
            monitorVo.setClinetNum(clientServerMapper.selectByPrimaryKey(rtspAddress.getClientId()).getClientNum());
            if(StringUtils.equals(type,"done")){
                streamService.pushDone(monitorVo);
            }else {
                streamService.push(monitorVo);
            }
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
