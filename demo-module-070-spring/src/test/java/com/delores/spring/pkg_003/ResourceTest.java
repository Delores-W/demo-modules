package com.delores.spring.pkg_003;

import com.delores.spring.pkg_002.service.InjectionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author William
 * @date 2020/1/8 1:52 PM
 * @description
 */
public class ResourceTest {

    @Test
    public void resourceTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"WEB-INF/spring-resource.xml"});
        MockResource mockResource = (MockResource) context.getBean("mockResource");
        try {
            mockResource.resource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
