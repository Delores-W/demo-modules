package com.delores.dynamic_proxy;

import com.delores.static_proxy.Rent;

/**
 * @author William
 * @date 3/25/21 8:36 PM
 * @description
 */
public class Landlord implements Rent {

    public void rent() {
        System.out.println("房东出租的房子");
    }
}
