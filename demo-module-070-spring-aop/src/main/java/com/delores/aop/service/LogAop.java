package com.delores.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 4/6/21 6:25 PM
 * @description
 */
@Aspect
// 开启Spring AOP
@EnableAspectJAutoProxy
@Component
public class LogAop {

    @Pointcut("execution(* com.delores.aop.service.UserServiceImpl.*(..))")
    public void logAop() {}

    @Before("logAop()")
    public void doBefore() {
        System.out.println("before service");
    }

    @After("logAop()")
    public void doAfter() {
        System.out.println("after service");
    }

    @Around("logAop()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕在方法之前执行");
        Object object = proceedingJoinPoint.proceed();
        System.out.println("环绕在方法之后执行");
        return object;
    }

    @AfterReturning("logAop()")
    public void doRun() {
        System.out.println("方法正常运行");
    }

    @AfterThrowing("logAop()")
    public void doThrow() {
        System.out.println("方法异常");
    }
}
