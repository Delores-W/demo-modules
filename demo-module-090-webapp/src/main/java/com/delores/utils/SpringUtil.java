package com.delores.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author William
 * @date 8/31/21 12:19 PM
 * @description
 */
public class SpringUtil {

    private static final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    /*
     * 从IOC容器中获取组件
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);

    }
}
