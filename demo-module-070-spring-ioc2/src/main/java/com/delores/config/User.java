package com.delores.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 3/25/21 4:52 PM
 * @description
 */
@Component
@Lazy
@Scope("prototype")
public class User {

    public User() {
        System.out.println("创建对象");
    }

    @Value("Delores")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
