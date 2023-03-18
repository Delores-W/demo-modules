package com.delores.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author William
 * @date 4/6/21 12:26 PM
 * @description
 */
public class MyFactoryBean implements FactoryBean<ThirdPartClass> {
    public ThirdPartClass getObject() throws Exception {
        return new ThirdPartClass();
    }

    public Class<?> getObjectType() {
        return ThirdPartClass.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
