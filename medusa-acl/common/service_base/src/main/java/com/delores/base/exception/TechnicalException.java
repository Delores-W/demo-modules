package com.delores.base.exception;


import com.delores.base.utils.enums.StatusCode;

/**
 * @author William
 * @date 5/2/21 12:52 AM
 * @description class wrapping Exception, it is throed when system detect some c
 * system errors ( runtime, database connection exceptions , etc.. )
 * 系统异常，框架异常，一些运行时异常等
 */
public class TechnicalException extends BaseMedusaException {

    public TechnicalException() {
        super();
    }

    public TechnicalException(Exception e) {
        super(e);
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(int errorCode, String message) {
        super(errorCode, message);
    }

    public TechnicalException(StatusCode statusCode) {
        super(statusCode);
    }

    public TechnicalException(String message, Exception e) {
        super(message, e);
    }
}
