package com.wang.smart.common;

import java.util.List;


public class RestPageResult<T> {

    private Integer code;

    private String msg;

    private List<T> data;

    private int total = 0;

    public RestPageResult(Integer errCode, String msg, int total, List<T> data) {
        this.code = errCode;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public RestPageResult() {
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static <T> RestPageResult<T> success(List<T> data, int total) {
        return new RestPageResult(ErrorCode.SUCCESS, (String)null, total, data);
    }

    public static <T> RestPageResult<T> empty() {
        return new RestPageResult(ErrorCode.SUCCESS, (String)null, 0, (List)null);
    }

    public static <T> RestPageResult<T> pageError(Integer code, String msg) {
        return new RestPageResult(code, msg, 0, (List)null);
    }
}