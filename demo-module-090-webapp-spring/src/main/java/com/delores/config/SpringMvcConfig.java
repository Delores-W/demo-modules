package com.delores.config;

import com.delores.interceptor.MyFirstInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author William
 * @date 4/7/21 11:37 PM
 * @description
 */
@Configuration
@ComponentScan({"com.delores.controller","com.delores.service"})
@Import(ThreadPoolConfig.class)
@EnableWebMvc
@EnableAsync
public class SpringMvcConfig implements WebMvcConfigurer {
    // Spring MVC config

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/pages/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

    @Bean
    public MyFirstInterceptor myFirstInterceptor() {
        return new MyFirstInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myFirstInterceptor()).addPathPatterns("/**"); // 拦截所有请求
    }
}
