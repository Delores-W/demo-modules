package com.example.exception.service;

import org.springframework.stereotype.Service;

/**
 * @author William
 * @date 2020/9/7 2:57 PM
 * @description
 */
@Service
public class SimpleService {

    public void testService() {
        System.out.println("Simple Service Invoked");

        // 系统不可预知异常
         throw new NullPointerException();

        // 模拟系统不可知异常
        // throw new RuntimeException("Unknown Exception");

        // 可预知异常
//        throw new CloudException(StatusCode.LoginFail);

    }
}
