package com.delores.spring.pkg_002.service;

import com.delores.spring.pkg_001.interfaces.BaseTest;
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
public class InjectionTest extends BaseTest {

    public InjectionTest() {
//        super("WEB-INF/spring-DI.xml");
        super("WEB-INF/spring-DI-Autowiring.xml");
    }

    @Test
    public void injectionTest() {
        InjectionService injectionService = getBean(InjectionService.class);
        injectionService.save("Delores");
    }

    @Test
    public void injectionAutowiringTest() {
        InjectionService injectionService = getBean(InjectionService.class);
        injectionService.save("Delores");
    }


}
