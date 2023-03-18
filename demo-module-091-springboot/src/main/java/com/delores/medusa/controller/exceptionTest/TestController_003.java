package com.delores.medusa.controller.exceptionTest;

import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author William
 * @date 2020/9/7 6:37 PM
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/exception_003")
public class TestController_003{

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/test_003")
    public BaseResponse<String> test() throws BaseMedusaException {

        log.info("Test3 Controller invoked");
        BaseResponse<String> response = null;

        simpleService.testService();

        return response;
    }

    // 可以用 控制器增强 @ControllerAdvice
//    @ExceptionHandler
//    public BaseResponse<String> handleException(Exception ex) {
//        System.out.println("Handle Exception Success");
//
//        if (ex instanceof CloudException) {
//            return new BaseResponse<>(ex);
//        } else {
//            return new BaseResponse<>(StatusCode.SYSTEM_ERROR);
//        }
//    }
}
