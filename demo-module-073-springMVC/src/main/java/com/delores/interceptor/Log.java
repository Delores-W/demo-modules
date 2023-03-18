package com.delores.interceptor;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author William
 * @date 9/2/21 3:53 AM
 * @description
 */
public class Log implements MethodBeforeAdvice {

    /**
     *
     * @param method 要执行目标对象的方法
     * @param args 方法的参数
     * @param target 目标对象
     * @throws Throwable none
     */
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + " 执行了 " + method.getName());
    }
}
