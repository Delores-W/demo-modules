package com.delores.spring.pkg_005;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author William
 * @date 2020/1/8 9:34 PM
 * @description
 */
@Component
public class Chinese_2 {

    @Delores
    // 使用 CGLIB 动态代理
    public String sayHello(String name) {
        System.out.println("-- sayHello() --");
        return name + " hello, AOP";

//        RequestContextHolder
    }

    public void eat(String food) {
        System.out.println("我正在吃：" + food);
    }
}
