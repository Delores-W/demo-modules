package com.delores.medusa.controller.exceptionTest;

import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.CloudException;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 2020/9/7 5:45 PM
 * @description
 */
@RestController
@RequestMapping("/exception_002")
@Slf4j
public class TestController_002{

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/test_002")
    public BaseResponse<String> test() throws BaseMedusaException {

        log.info("Test2 Controller invoked");
        BaseResponse<String> response = null;

        simpleService.testService();

        return response;
    }

    // 如果有需要定制返回消息 可以在方法内对异常进行处理 ！！！

    // 可将异常处理提到BaseController 直接继承即可
    // 如果handle住 CloudException  后面的Exception 不会调用
//    @ExceptionHandler(CloudException.class)
//    public BaseResponse<String> handleCloudException(CloudException e) {
//        // 系统可预知异常 程序主动抛出
//
//        log.error(e.getMessage());
//
//        e.printStackTrace();
//
//        return BaseResponse.exceptionResponse(e);
//    }
//
//    @ExceptionHandler
//    public BaseResponse<String> handleCloudException(Exception e) {
//        // 系统可预知异常 程序主动抛出
//
//        log.error(e.getMessage());
//
//        e.printStackTrace();
//
//        return BaseResponse.exceptionResponse(e);
//    }

    @ExceptionHandler
    public BaseResponse<String> handleException(Exception ex) {
        if (ex instanceof CloudException) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return new BaseResponse<>(((CloudException) ex).getCode(), ex.getMessage());
        } else {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return new BaseResponse<>(StatusCode.SYSTEM_ERROR);
        }
    }
}
