package com.wang.gateway.service.impl;

import com.wang.core.common.ErrorCodes;
import com.wang.core.common.ResultCode;
import com.wang.core.util.Md5Util;
import com.wang.gateway.dao.UserMapper;
import com.wang.gateway.dto.UserDTO;
import com.wang.gateway.entity.User;
import com.wang.gateway.jwt.JwtUtil;
import com.wang.gateway.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    //@Autowired
   // private UserMapper userMapper;

    @Override
    public ResultCode login(UserDTO userDTO) {
        // 验证用户是否存在
        /*User user = userMapper.selectByUserName(userDTO.getUserName());
        if(user==null){
            return new ResultCode(ErrorCodes.NO_DATA);
        }
        //判断密码是否正确
        String password = userDTO.getPassword();
        String encrypt = user.getPassword();
        password = Md5Util.encryptPassword(password, encrypt);
        if (!password.equals(user.getPassword())) {
            return new ResultCode(ErrorCodes.PASSWORD_ERROR);
        }
        String token = null;*/
        /*try {
            token = jwtTokenUtil.generateToken(appUser);
        } catch (Exception e) {
            throw TybootErrorCodesException.SYSTEM_ERROR(err->err.build());
        }*/
        return null;
        //账号登录
        /*if(ToolUtil.checkMobile(accountOrPhone) || accountOrPhone.startsWith("9")){
            AppUser appUser = appUserMapper.selectByAccountOrPhone(accountOrPhone);
            if (null == appUser) {
                logger.error("用户信息不存在或被禁止登陆");
                throw TybootErrorCodesException.USER_NOT_EXIST(err->err.build());
            } else {
                if(appUser.getDisabledStatus().equals("Y")) {
                    throw TybootErrorCodesException.PROCESS_FAILED_MESSAGE(err->err.setMessage("您的账户已被禁止登录!"));
                }
                // 未绑定手机
                if(accountOrPhone.startsWith("9") && appUser.getStatus().equals("0")) {
                    String token = null;
                    try {
                        token = jwtTokenUtil.generateToken(appUser);
                    } catch (Exception e) {
                        throw TybootErrorCodesException.SYSTEM_ERROR(err->err.build());
                    }
                    tokenCommon(appUser, token);
                    ResultData resultDate = new ResultData(token, appUser);
                    resultDate.setBinding(false);
                    return new AppResult(StatusCode.SUCCESS, resultDate);
                }
                if (appUser.getRoleId().equals("EMPLOYEE")) {
                    switch (appUser.getAuditStatus()) {
                        case "0":
                            throw TybootErrorCodesException.PROCESS_FAILED_MESSAGE(err -> err.setMessage("您的资料正在审核中，请耐心等待!"));
                        case "2":
                            throw TybootErrorCodesException.PROCESS_FAILED_MESSAGE(err -> err.setMessage("您的审核申请未通过，请联系企业管理员!"));
                        default:
                            break;
                    }
                }
                //判断密码是否正确
                String password = authRequest.getPassword();
                String encrypt = appUser.getSalt();
                password = Md5Util.encryptPassword(password, encrypt);
                if (!password.equals(appUser.getPassword())) {
                    throw TybootErrorCodesException.PASSWORD_ERROR(err->err.build());
                }
                String token = null;
                try {
                    token = jwtTokenUtil.generateToken(appUser);
                } catch (Exception e) {
                    throw TybootErrorCodesException.SYSTEM_ERROR(err->err.build());
                }

                appUser.setHeadPortrait(OSSUtil.addUrlRealName(appUser.getHeadPortrait()));
                ResultData resultDate = new ResultData(token, appUser);
                // token 处理
                tokenCommon(appUser, token);

                //插入登陆log信息
                AppLoginLog appLoginLog=new AppLoginLog();
                appLoginLog.setLoginAccount(appUser.getAccount());
                appLoginLog.setLoginName(appUser.getName());
                appLoginLog.setLoginType(LoginEnum.ACOUNT_LOGIN.getCode());
                appLoginLog.setLoginUserId(appUser.getId());
                appLoginLog.setLoginTime(new Date());
                appLoginLogMapper.insertSelective(appLoginLog);
                if (appUser.getRealStatus().equals("N")) {
                    return new AppResult(10102017, "您尚未实名认证!", resultDate);
                }
                switch (appUser.getAuditStatus()) {
                    case "3":
                        // 未完善资料
                        return new AppResult(10102017, "您尚未完善个人信息!", resultDate);
                    default:
                        break;
                }
                return new AppResult(StatusCode.SUCCESS, resultDate);
            }
        }else{
            throw TybootErrorCodesException.PARAM_ERROR(err->err.setMessage("账户格式错误"));
        }*/
    }
}
