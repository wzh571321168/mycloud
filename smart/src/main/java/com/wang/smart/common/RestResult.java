package com.wang.smart.common;



public class RestResult<T> {

    private Integer code;

    private String msg;

    private T data;
    public static final RestResult<Void> VOID_SUCCESS_RESULT;

    public RestResult() {
    }

    public RestResult(Integer errCode, String msg, T data) {
        this.code = errCode;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> RestResult<T> error(Integer errCode, String msg) {
        return new RestResult(errCode, msg, (Object)null);
    }

    public static <T> RestResult<T> success(T data) {
        return new RestResult(ErrorCode.SUCCESS, "操作成功", data);
    }

    static {
        VOID_SUCCESS_RESULT = new RestResult(ErrorCode.SUCCESS, "", (Object)null);
    }
}