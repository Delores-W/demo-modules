package com.delores.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author William
 * @date 3/31/21 2:36 PM
 * @description
 */
public class ServletContextListenerImpl implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext 初始化");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext 销毁");
    }
}
