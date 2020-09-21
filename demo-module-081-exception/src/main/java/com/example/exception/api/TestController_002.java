package com.example.exception.api;

import com.example.exception.model.CloudException;
import com.example.exception.model.response.BaseResponse;
import com.example.exception.service.SimpleService;
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
public class TestController_002 extends BaseController{

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/test_002")
    public BaseResponse<String> test() {

        LOGGER.info("Test2 Controller invoked");
        BaseResponse<String> response = new BaseResponse<>("Delores");

        simpleService.testService();

        return response;
    }

    // 如果有需要定制返回消息 可以在方法内对异常进行处理 ！！！

    // 可将异常处理提到BaseController 直接继承即可
    // 如果handle住 CloudException  后面的Exception 不会调用
    @ExceptionHandler(CloudException.class)
    public BaseResponse<String> handleCloudException(CloudException e) {
        // 系统可预知异常 程序主动抛出

        LOGGER.error(e.getMessage());

        e.printStackTrace();

        return new BaseResponse<>(e);
    }

    @ExceptionHandler
    public BaseResponse<String> handleCloudException(Exception e) {
        // 系统可预知异常 程序主动抛出

        LOGGER.error(e.getMessage());

        e.printStackTrace();

        return new BaseResponse<>(e);
    }
}
