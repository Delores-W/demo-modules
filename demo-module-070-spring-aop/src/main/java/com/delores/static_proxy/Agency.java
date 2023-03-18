package com.delores.static_proxy;

/**
 * @author William
 * @date 3/25/21 8:38 PM
 * @description
 */
public class Agency implements Rent {

    private final Landlord landlord;

    public Agency(Landlord landlord) {
        this.landlord = landlord;
    }

    // 代理方法，调用被代理对象的实现方法,并添加收费方法
    public void rent() {
        landlord.rent();
        agencyFee();
    }

    private void agencyFee() {
        System.out.println("中介（代理）提供服务，收取中介费。");
    }
}
