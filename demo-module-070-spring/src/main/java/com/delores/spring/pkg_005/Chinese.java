package com.delores.spring.pkg_005;

import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 2020/1/8 5:28 PM
 * @description
 */
@Component
public class Chinese implements Person {

    @Delores
    @Override
    // 使用 JDK 动态代理
    public String sayHello(String name) {
        System.out.println("-- sayHello() --");
        return name + " hello, AOP";
    }

    public void eat(String food) {
        System.out.println("我正在吃：" + food);
    }
}
