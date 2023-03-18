package com.delores.config;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * @author William
 * @date 4/7/21 5:46 PM
 * @description
 */
@HandlesTypes(value = MyHandlerType.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

    /**
     * @param set 感兴趣类集合 继承MyHandlerType的类都会被放到这个集合
     * @param servletContext 上下文
     * @throws ServletException ex
     */
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

        for (Class<?> clazz : set){
            System.out.println("class: " + clazz);
        }

        // 手动添加Servlet Listener Filter

    }
}
