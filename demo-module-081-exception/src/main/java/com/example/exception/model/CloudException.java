package com.example.exception.model;

/**
 * @author William
 * @date 2020/9/7 11:51 AM
 * @description
 */
public class CloudException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    /**
     * error code
     */
    private Integer errorCode;

    /**
     * 构造一个基本异常
     *
     * @param message
     *          信息描述
     */
    public CloudException(String message) {
        super(message);
    }

    /**
     * 构造一个基本异常
     *
     * @param errorCode
     *          错误编码
     * @param message
     *          信息描述
     */
    public CloudException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造一个基本异常
     *
     * @param statusCode
     *          from Enum StatusCode
     */
    public CloudException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.errorCode = statusCode.getCode();
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
