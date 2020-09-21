package com.delores.spring.pkg_001.impl;

import com.delores.spring.pkg_001.interfaces.OneInterface;

/**
 * @author William
 * @date 2020/1/3 4:18 PM
 * @description
 */
public class OneInterfaceImpl implements OneInterface {

    @Override
    public String hello(String message) {
        return "msg from interface OneInterface: " + message;
    }
}
