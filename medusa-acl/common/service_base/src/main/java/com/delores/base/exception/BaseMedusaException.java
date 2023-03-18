package com.delores.base.exception;

import com.delores.base.utils.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author William
 * @date 5/2/21 12:43 AM
 * @description This is the base exceptional class which is extended by all other exception classes.
 */
@Slf4j
public class BaseMedusaException extends RuntimeException {

    /**
     * error code
     */
    private int code;

    /**
     * error message
     */
    private String message;

    public BaseMedusaException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        log.error(toString());
    }

    public BaseMedusaException(int code, String message, Exception e) {
        super(message, e);
        this.code = code;
        this.message = message;
        log.error(toString());
    }

    public BaseMedusaException(StatusCode statusCode) {
        super(statusCode.getMsg());
        this.message = statusCode.getMsg();
        this.code = statusCode.getCode();
        log.error(toString());
    }

    public BaseMedusaException(StatusCode statusCode, Exception e) {
        super(statusCode.getMsg(), e);
        this.message = statusCode.getMsg();
        this.code = statusCode.getCode();
        log.error(toString());
    }

    public BaseMedusaException() {
        this(StatusCode.FAIL);
    }

    public BaseMedusaException(Exception e) {
        this(StatusCode.FAIL, e);
    }

    public BaseMedusaException(String message) {
        this(StatusCode.FAIL.getCode(), message);
    }

    public BaseMedusaException(String message, Exception e) {
        this(StatusCode.FAIL.getCode(), message, e);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "CODE [" + getCode() + "] MSG: " + getMessage() + " => " + super.toString();
    }
}
