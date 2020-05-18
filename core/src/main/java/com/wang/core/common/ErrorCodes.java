package com.wang.core.common;

/**
 * 错误码枚举类
 *
 */
public enum ErrorCodes {
    /**
     * 1 04 01 001
     * | |  |  |
     * | |  |  +---------序号：001~999
     * | |  +------------子系统名：01~系统通用,02~账户子系统,03-业务子系统
     * | +---------------应用名：01:-rest 02:admin
     * +-----------------大类：1：服务类
     */
    OK(0, "SUCCESS"),
    FAILED(-1, "操作失败"),
    // 通用型错误码
    SYSTEM_ERROR(10101001, "系统错误"),
    SYSTEM_ERROR_MESSAGE(10101002, "[message]"),
    REJECT_REQUEST(10101003, "无权限访问"),
    PROCESS_FAILED(10101004, "处理失败"),
    PROCESS_FAILED_MESSAGE(10101005, "[message]"),
    TOKEN_NOT_EXIST(10101006, "token不存在"),
    TOKEN_EXPIRED(10101007, "token超时"),
    TOKEN_ERROR(10101008, "token校验失败"),
    REQUEST_HEADER_NOT_EXIST(10101009, "请求头不存在"),
    DECRYPT_PARAMETERS_ERROR(10101010, "请求参数解析失败"),
    URL_NOT_EXIST(10101011, "url不存在"),
    NO_DATA(10101012, "未查询到数据"),
    MOBILE_NOT_EXIST(10101013, "手机号码为空"),
    PARAM_NOT_ERROR(10101014, "必填参数为空"),
    VERIFICATION_CODE_ERROR(10101015, "发送短信验证码错误"),

    // 账户
    USER_NOT_EXIST(10102010, "用户不存在"),
    PASSWORD_ERROR(10102011, "密码错误"),
    VERIFY_CODE_ERROR(10102012, "验证码错误"),
    VERIFY_CODE_EXPIRED(10102013, "验证码过期,请重新发送"),
    USER_EXISTED(10102014, "用户已经存在"),
    PARAM_ERROR(10102015, "[message]"),
    PASSWORD_IDENTICAL_ERROR(10102016, "两次密码输入不一致"),
    NOT_REAL_NAME_MESSAGE(10102017, "[message]"),

    // 业务
    PRD_INVALID(10103001,"产品已失效"),
    PRDUCT_ERROR_MSSAGE(10103002, "[message]"),
    PRDUCT_NOT_EXIST(10103003, "产品不存在"),
    PRDUCT_STATUS_WRONG(10103004, "产品状态错误"),
    ;

    private final int errorNo;

    private final String errorFormat;

    ErrorCodes(int errorNo, String errorFormat) {
        this.errorNo = errorNo;
        this.errorFormat = errorFormat;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public String getFormat() {
        return errorFormat;
    }
}
