package com.wang.gateway.service;


import com.wang.gateway.common.RestResult;
import com.wang.gateway.dto.UserDTO;

public interface UserService {
    RestResult login(UserDTO userDTO);
}
