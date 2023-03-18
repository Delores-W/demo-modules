package com.delores.medusa.exception;

import com.delores.medusa.model.enums.StatusCode;

/**
 * @author William
 * @date 2020/9/7 11:51 AM
 * @description
 */
public class CloudException extends BaseMedusaException {

    public CloudException() {
        super();
    }

    public CloudException(Exception e) {
        super(e);
    }

    public CloudException(String message) {
        super(message);
    }

    public CloudException(int errorCode, String message) {
        super(errorCode, message);
    }

    public CloudException(StatusCode statusCode) {
        super(statusCode);
    }

}
