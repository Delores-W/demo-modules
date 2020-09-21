package com.delores.spring.pkg_005;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 2020/1/8 9:35 PM
 * @description
 */
@Component
public class Test_2 {

    @Autowired
    private Chinese_2 chinese_2;

    public void test() {
        chinese_2.sayHello("listen");
        System.out.println(chinese_2.getClass());
    }
}
