package com.delores.spring.pkg_002.service;

import com.delores.spring.pkg_001.interfaces.OneInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * @author William
 * @date 2020/1/7 4:55 PM
 * @description
 */
public class InjectionTest {

    @Test
    public void injectionTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"WEB-INF/spring-DI.xml"});
        InjectionService injectionService = (InjectionService) context.getBean("injectionService");
        injectionService.save("Delores");
    }

    @Test
    public void injectionAutowiringTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"WEB-INF/spring-DI-Autowiring.xml"});
        InjectionService injectionService = (InjectionService) context.getBean("injectionService");
        injectionService.save("Delores");
    }


}
