package com.wang.gateway.dao;

import com.wang.gateway.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User selectByUserName(@Param("userName") String userName);
}