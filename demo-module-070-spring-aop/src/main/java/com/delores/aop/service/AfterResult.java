package com.delores.aop.service;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author William
 * @date 3/26/21 11:23 AM
 * @description
 */
public class AfterResult implements AfterReturningAdvice {
    public void afterReturning(Object result, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(method.getName() + "返回结果是: " + result);
    }
}
