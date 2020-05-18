package com.wang.core.common;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;


@Data
public class ResultCode<T> implements Serializable {
    /**
     * 1成功，其他失败
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
    
    public ResultCode() {
    }

    public boolean isSuccess() {
        return this.code == 0;
    }

    public static boolean isSuccess(Map<String, Object> result) {
        if (result.get("success_type") == null) {
            return false;
        }
        return Integer.parseInt((String) result.get("success_type")) == 0;
    }

    public ResultCode(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    public ResultCode(ErrorCodes statusCode) {
        new ResultCode( statusCode.getErrorNo(),statusCode.getFormat(),(Object)null);
    }

    public ResultCode(ErrorCodes statusCode, T o) {
        this.code = statusCode.getErrorNo();
        this.msg = statusCode.getFormat();
        this.data = o;
    }

    public static <T> ResultCode<T> error(Integer errCode, String msg) {
        return new ResultCode(errCode, msg, (Object)null);
    }

    public static <T> ResultCode<T> success(T data) {
        return new ResultCode(ErrorCodes.OK.getErrorNo(), "操作成功", data);
    }

}
