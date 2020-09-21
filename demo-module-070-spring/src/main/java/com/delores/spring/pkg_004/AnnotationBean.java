package com.delores.spring.pkg_004;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 2020/1/8 2:20 PM
 * @description
 */
@Scope
@Component
public class AnnotationBean {

    public void test() {
        System.out.println("invoke AnnotationBean test method");
    }
}
