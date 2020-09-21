package com.delores.spring.pkg_003;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author William
 * @date 2020/1/8 1:41 PM
 * @description
 */
public class MockResource implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // ************************************************************************
    // 也可以用 @Autowired 来获得 ApplicationContext BeanFactory MessageSource **
    // 不能用于 BeanPostProcessor 会循环依赖                                    **
    // ************************************************************************

    public void resource() throws IOException {
//        Resource resource = applicationContext.getResource("classpath:config.txt");
//        Resource resource = applicationContext.getResource("config.txt");
        Resource resource = applicationContext.getResource("file:/Users/delores/Hubs/Art/A_Projects/demo-modules/demo-module-070-spring/src/main/resources/WEB-INF/spring-resource.xml");
        System.out.println(resource.getFilename());
        System.out.println(resource.contentLength());
    }
}
