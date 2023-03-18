package com.delores.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author William
 * @date 2021/11/29 11:16 AM
 * @description
 */
public class DemoServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext 初始化");
        ServletContext servletContext = sce.getServletContext();
        String initParameter = servletContext.getInitParameter("name");
        System.out.println("initParameter: " + initParameter);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
