package com.delores.spring.pkg_005;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 2020/1/8 5:37 PM
 * @description
 */
@Aspect
@Component
public class AdviceTest {

    @Pointcut("@annotation(com.delores.spring.pkg_005.Delores)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }
}
