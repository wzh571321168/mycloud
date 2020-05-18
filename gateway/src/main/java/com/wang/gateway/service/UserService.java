package com.wang.gateway.service;

import com.wang.core.common.ResultCode;
import com.wang.gateway.dto.UserDTO;

public interface UserService {
    ResultCode login(UserDTO userDTO);
}
