package com.delores.spring.pkg_001.test;

import com.delores.spring.pkg_001.impl.OneInterfaceImpl;
import com.delores.spring.pkg_001.interfaces.OneInterface;

/**
 * @author William
 * @date 2020/1/3 4:24 PM
 * @description
 */
public class Main {

    public static void main(String[] args) {
        // 面向接口编程实例 多态的实现
        OneInterface oneInterface = new OneInterfaceImpl();
        String result = oneInterface.hello("This is my Delores");
        System.out.println(result);
    }
}
