package com.delores.medusa.controller;


import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.CloudException;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author William
 * @date 2020/9/8 10:15 AM
 * @description
 * ----------- 控制器增强 -------------
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public BaseResponse<String> handleException(Exception ex) {
        log.info("Handle Global Exception Success");

        if (ex instanceof BaseMedusaException) {
//            ex.printStackTrace();
//            log.error(ex.getMessage());
            return new BaseResponse<>(((BaseMedusaException) ex).getCode(), ex.getMessage());
        } else {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return new BaseResponse<>(StatusCode.SYSTEM_ERROR, ex.getMessage());
        }
    }
}
