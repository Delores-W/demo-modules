package com.delores.medusa.controller;

import com.delores.medusa.model.TestAutoWired;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 4/8/21 7:05 PM
 * @description
 */
@Slf4j
@RestController
public class HelloController {

//    @Secured("admin")
//    @PreAuthorize("has")
    @GetMapping("/hello")
    public String hello() {
        log.info("invoke hello method");
        log.error("error test");

//        TestAutoWired testAutoWired = new TestAutoWired();
//        testAutoWired.test();

        return "Hello Delores";
    }

    //访问需要被授权的资源
    @RequestMapping(value = "/token/auth", method = RequestMethod.GET)
    public BaseResponse<String> tokenAuth() {
        BaseResponse<String> response = new BaseResponse<>(StatusCode.SUCCESS);
        try {
            String info = "数据库+token~成功访问需要被拦截的链接/资源";
            response.setData(info);

        } catch (Exception e) {
            response = new BaseResponse<>(StatusCode.FAIL, e.getMessage());
        }
        return response;
    }

    //访问不需要被授权的资源
    @RequestMapping(value = "/token/unauth", method = RequestMethod.GET)
    public BaseResponse<String> tokenUnauth() {
        BaseResponse<String> response = new BaseResponse<>(StatusCode.SUCCESS);
        try {
            String info = "数据库+token~成功访问不需要被拦截的链接/资源";
            response.setData(info);

        } catch (Exception e) {
            response = new BaseResponse<>(StatusCode.FAIL, e.getMessage());
        }
        return response;
    }
}
