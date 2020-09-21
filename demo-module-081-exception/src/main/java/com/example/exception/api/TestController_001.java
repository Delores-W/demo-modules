package com.example.exception.api;

import com.example.exception.model.CloudException;
import com.example.exception.model.response.BaseResponse;
import com.example.exception.service.SimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 2020/9/7 1:49 PM
 * @description
 */
@RestController
@RequestMapping("/exception_001")
public class TestController_001 extends BaseController{

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/test_001")
    public BaseResponse<String> test() {

        LOGGER.info("Test1 Controller invoked");
        BaseResponse<String> response = new BaseResponse<>("Delores");

        try {
            simpleService.testService();

        } catch (CloudException e) {
            // 系统可预知异常 程序主动抛出

            LOGGER.error(e.getMessage());

            e.printStackTrace();

            return new BaseResponse<>(e);
        } catch (Exception e) {
            // 对于系统不可预知异常 统一消息返回 e.g. System Error, Please contract administrator.

            // 日志记录
            LOGGER.error(e.getMessage());

            // Trace Error
            e.printStackTrace();

            // Friendly Response
            return new BaseResponse<>(e);
        }

        return response;
    }
}
