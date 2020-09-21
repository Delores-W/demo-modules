package com.example.exception.api;

import com.example.exception.model.CloudException;
import com.example.exception.model.StatusCode;
import com.example.exception.model.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author William
 * @date 2020/9/8 10:15 AM
 * @description
 * ----------- 控制器增强 -------------
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public BaseResponse<String> handleException(Exception ex) {
        LOGGER.info("Handle Global Exception Success");

        if (ex instanceof CloudException) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
            return new BaseResponse<>(ex);
        } else {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
            return new BaseResponse<>(StatusCode.SYSTEM_ERROR);
        }
    }
}
