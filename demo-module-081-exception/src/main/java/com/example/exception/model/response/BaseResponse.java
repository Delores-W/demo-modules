package com.example.exception.model.response;

import com.example.exception.model.StatusCode;

import java.io.Serializable;

/**
 * @author William
 * @date 2020/9/4 6:10 PM
 * @description 统一的数据响应类型 统一的报文格式
 */
public class BaseResponse<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse() {}

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    // SUCCESS
    public BaseResponse(T data) {
        this(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), data);
    }

    public BaseResponse(Throwable e) {
        this(StatusCode.FAIL.getCode(), e.getMessage(), null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
