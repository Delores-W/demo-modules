package com.example.demo.util;

import java.net.URL;

/**
 * @author William
 * @date 2021/12/3 1:24 PM
 * @description
 */
public class TestClassLoader {

    public static void main(String[] args) {

        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println(classLoader);

        URL resource = classLoader.getResource("");
        System.out.println(resource);


//        System.out.println(TestClassLoader.class.getResource("ehcache.xml"));
//        System.out.println(TestClassLoader.class.getResource("/ehcache.xml"));
//        System.out.println();
//        System.out.println(TestClassLoader.class.getClassLoader().getResource("ehcache.xml"));
//        System.out.println(TestClassLoader.class.getClassLoader().getResource("/ehcache.xml"));

    }
}
