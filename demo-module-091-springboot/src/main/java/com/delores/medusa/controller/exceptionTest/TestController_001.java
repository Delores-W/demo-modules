package com.delores.medusa.controller.exceptionTest;

import com.delores.medusa.exception.BaseMedusaException;
import com.delores.medusa.exception.CloudException;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.service.SimpleService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TestController_001{

    @Autowired
    private SimpleService simpleService;

    @PostMapping("/test_001")
    public BaseResponse<String> test() {

        log.info("Test1 Controller invoked");
        BaseResponse<String> response = null;

        try {
            simpleService.testService();

        } catch (BaseMedusaException e) {
            // 系统可预知异常 程序主动抛出

            log.error(e.getMessage());

            e.printStackTrace();

            return BaseResponse.exceptionResponse(e);
        } catch (Exception e) {
            // 对于系统不可预知异常 统一消息返回 e.g. System Error, Please contract administrator.

            // 日志记录
            log.error(e.getMessage());

            // Trace Error
            e.printStackTrace();

            // Friendly Response
            return BaseResponse.exceptionResponse(e);
        }

        return response;
    }
}
