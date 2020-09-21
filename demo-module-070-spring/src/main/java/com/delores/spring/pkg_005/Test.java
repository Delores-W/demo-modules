package com.delores.spring.pkg_005;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 2020/1/8 5:44 PM
 * @description
 */
@Component
public class Test {

    @Autowired
    private Person chinese;

    public void test() {
        chinese.sayHello("listen");
        System.out.println(chinese.getClass());
    }
}
