package com.delores.medusa.model.response;

import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.CloudException;
import com.delores.medusa.model.enums.StatusCode;
import lombok.Data;

/**
 * @author William
 * @date 2019-10-10 17:01
 * @description 统一的响应数据模型
 */
@Data
public class BaseResponse<T> {

    private Integer code;
    private String msg;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(StatusCode statusCode, T data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    public BaseResponse(StatusCode statusCode, String msg) {
        this.code = statusCode.getCode();
        this.msg = msg;
    }

    public BaseResponse(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    public static BaseResponse<String> SUCCESS() {
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    public static BaseResponse<String> FAIL() {
        return new BaseResponse<>(StatusCode.FAIL);
    }

//    public BaseResponse(Throwable e) {
////            this(((CloudException) e).getErrorCode(), e.getMessage(), null);
//        if (e instanceof CloudException) {
//            new BaseResponse<T>(((CloudException) e).getErrorCode(), e.getMessage(), null);
//        } else {
//            new BaseResponse<T>(StatusCode.FAIL.getCode(), e.getMessage(), null);
//        }
//    }

    //
    public static <T> BaseResponse<T> exceptionResponse(Throwable e) {
        if (e instanceof BaseMedusaException) {
            return new BaseResponse<>(((BaseMedusaException) e).getCode(), e.getMessage(), null);
        } else {
            return new BaseResponse<>(StatusCode.FAIL.getCode(), e.getMessage(), null);
        }
    }
}