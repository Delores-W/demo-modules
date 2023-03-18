package com.delores.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 9/2/21 2:51 AM
 * @description
 */
public class MethodInvokeInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before method invoke....");
        Object object = invocation.proceed();
        System.out.println("after method invoke.....");
        return object;
    }
}
