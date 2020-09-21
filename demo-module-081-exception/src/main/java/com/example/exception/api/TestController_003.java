package com.example.exception.api;

import com.example.exception.model.CloudException;
import com.example.exception.model.StatusCode;
import com.example.exception.model.response.BaseResponse;
import com.example.exception.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author William
 * @date 2020/9/7 6:37 PM
 * @description
 */
@RestController
@RequestMapping("/exception_003")
public class TestController_003 extends BaseController {

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/test_003")
    public BaseResponse<String> test() {

        LOGGER.info("Test3 Controller invoked");
        BaseResponse<String> response = new BaseResponse<>("Delores");

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
