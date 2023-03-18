package com.delores.base.response;

import com.delores.base.exception.BaseMedusaException;
import com.delores.base.utils.enums.StatusCode;
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

    public static <T> BaseResponse<T> SUCCESS() {
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    public static <T> BaseResponse<T> FAIL() {
        return new BaseResponse<>(StatusCode.FAIL);
    }

    public BaseResponse(Throwable e) {
        if (e instanceof BaseMedusaException) {
            // 构造方法要再返回构造方法的对象，否则构造对象的值已经初始化
            //new BaseResponse<T>(((BaseMedusaException) e).getCode(), e.getMessage(), null);
            this.code = ((BaseMedusaException) e).getCode();
        } else {
            this.code = StatusCode.FAIL.getCode();
        }
        this.msg = e.getMessage();
    }

    //
//    public static <T> BaseResponse<T> exceptionResponse(Throwable e) {
//        if (e instanceof BaseMedusaException) {
//            return new BaseResponse<>(((BaseMedusaException) e).getCode(), e.getMessage(), null);
//        } else {
//            return new BaseResponse<>(StatusCode.FAIL.getCode(), e.getMessage(), null);
//        }
//    }


}
