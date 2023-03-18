package com.delores.spring.pkg_001.interfaces;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author William
 * @date 2020/1/3 4:41 PM
 * @description
 */
public class BaseTest {

    private ClassPathXmlApplicationContext context;
    private String springXmlPath;

    public BaseTest() {
    }

    public BaseTest(String springXmlPath) {
        this.springXmlPath = springXmlPath;
    }

    @Before
    public void before() {
        if (StringUtils.isEmpty(springXmlPath)) {
            springXmlPath = "classpath*:spring-*.xml";
        }

        try {
            context = new ClassPathXmlApplicationContext(springXmlPath.split("[,\\s]"));
            context.start();
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }

    @After
    public void after() {
        context.close();
    }

    @SuppressWarnings("unchecked")
    protected <T> T getBean(String beanId) {
        return (T) context.getBean(beanId);
    }

    protected <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

}
