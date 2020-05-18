package com.wang.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 描述: 用户DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userName;

    private String password;

    private String mobile;

}
