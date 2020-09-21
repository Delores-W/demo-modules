package com.delores.spring.pkg_004;

import com.delores.spring.pkg_003.MockResource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author William
 * @date 2020/1/8 2:23 PM
 * @description
 */
public class AnnotationTest {

    @Test
    public void resourceTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"WEB-INF/spring-DI-Annotation.xml"});
        AnnotationBean annotationBean = (AnnotationBean) context.getBean("annotationBean");
        annotationBean.test();
    }
}
