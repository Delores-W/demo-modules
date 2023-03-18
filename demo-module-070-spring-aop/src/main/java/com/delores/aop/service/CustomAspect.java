package com.delores.aop.service;

import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 3/26/21 3:29 PM
 * @description
 */
@Component
public class CustomAspect {

    public void beforeLog() {
        System.out.println("before method ...");
    }

    public void afterLog() {
        System.out.println("after method ...");
    }
}
