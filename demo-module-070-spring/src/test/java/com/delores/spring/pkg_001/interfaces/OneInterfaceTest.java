package com.delores.spring.pkg_001.interfaces;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author William
 * @date 2020/1/3 4:36 PM
 * @description
 */
public class OneInterfaceTest {

    @Test
    public void hello() {

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"WEB-INF/spring-ioc.xml"});
        OneInterface oneInterface = (OneInterface) context.getBean("oneInterface");
        System.out.println(oneInterface.hello("test message"));
    }
}