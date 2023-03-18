package com.delores.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author William
 * @date 4/7/21 11:39 PM
 * @description web.xml
 */
public class WebInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 启动SpringMVC 容器类注入到Spring中
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();

        // 注入配置类
        applicationContext.register(SpringMvcConfig.class);

        // 将DispatcherServlet注入到servlet容器
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(applicationContext));

        // 映射
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);;

    }
}
