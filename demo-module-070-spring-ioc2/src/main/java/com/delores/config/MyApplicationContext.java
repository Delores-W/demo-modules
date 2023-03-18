package com.delores.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 4/6/21 3:33 PM
 * @description
 */
@Component
public class MyApplicationContext implements ApplicationContextAware {
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ThirdPartClass bean = applicationContext.getBean(ThirdPartClass.class);
        System.out.println("class MyApplicationContext 实现了接口-ApplicationContextAware，获得了ApplicationContext");
    }
}
