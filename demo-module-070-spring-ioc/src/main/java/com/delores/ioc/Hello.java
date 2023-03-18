package com.delores.ioc;

/**
 * @author William
 * @date 3/24/21 12:23 AM
 * @description
 */
public class Hello {

    public Hello(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hello " + name;
    }
}
