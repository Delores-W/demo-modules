package com.delores.spring.pkg_005;

import com.delores.spring.pkg_004.AnnotationBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author William
 * @date 2020/1/8 5:43 PM
 * @description
 */
public class TestAOP {

    @Test
    public void resourceTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"WEB-INF/spring-DI-Annotation.xml"});

        com.delores.spring.pkg_005.Test test = (com.delores.spring.pkg_005.Test) context.getBean("test");
        test.test();

        Test_2 test_2 = (Test_2) context.getBean("test_2");
        test_2.test();
    }
}
