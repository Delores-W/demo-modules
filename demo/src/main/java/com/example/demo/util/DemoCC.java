package com.example.demo.util;

import com.delores.demo.Hello;

/**
 * @author William
 * @date 2021/12/4 11:42 PM
 * @description
 */
public class DemoCC {

    public String cc() {
        return "CC";
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        System.out.println(hello.hello());
    }
}
