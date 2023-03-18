package com.delores.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 3/26/21 3:59 PM
 * @description
 */

@Aspect
@Component
public class AnnotationAspect {

//    @Before("execution(* com.delores.aop.service.UserServiceImpl.*(..))")
//    public void before() {
//        System.out.println("方法执行前");
//    }
//
//    @After("execution(* com.delores.aop.service.UserServiceImpl.*(..))")
//    public void after() {
//        System.out.println("方法执行后");
//    }
//
//    @Around("execution(* com.delores.aop.service.UserServiceImpl.*(..))")
//    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        System.out.println("环绕前");
//        Object result = proceedingJoinPoint.proceed();
//        System.out.println(result);
//        // 获得方法信息
//        System.out.println(proceedingJoinPoint.getSignature());
//        System.out.println("环绕后");
//    }
}

