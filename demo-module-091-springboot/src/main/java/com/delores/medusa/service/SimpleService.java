package com.delores.medusa.service;

import com.delores.medusa.exception.BaseMedusaException;
import org.springframework.stereotype.Service;

/**
 * @author William
 * @date 2020/9/7 2:57 PM
 * @description
 */
@Service
public class SimpleService {

    public void testService() throws BaseMedusaException {
        System.out.println("Simple Service Invoked");

        // 系统不可预知异常
//         throw new NullPointerException();

        // 模拟系统不可知异常
         throw new BaseMedusaException("Unknown Exception");

        // 可预知异常
//        throw new CloudException(StatusCode.LoginFail);

    }
}
