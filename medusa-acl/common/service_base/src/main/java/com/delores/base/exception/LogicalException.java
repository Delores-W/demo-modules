package com.delores.base.exception;

import com.delores.base.utils.enums.StatusCode;

/**
 * @author William
 * @date 5/2/21 9:35 PM
 * @description class wrapping exception, throws in case of business errors
 * 比如条件范围内没有数据，参数传入问题等,是给调用者提供的信息异常
 */
public class LogicalException extends BaseMedusaException {

    public LogicalException() {
        super();
    }

    public LogicalException(Exception e) {
        super(e);
    }

    public LogicalException(String message) {
        super(message);
    }

    public LogicalException(int errorCode, String message) {
        super(errorCode, message);
    }

    public LogicalException(StatusCode statusCode) {
        super(statusCode);
    }

    public LogicalException(String message, Exception e) {
        super(message, e);
    }
}
