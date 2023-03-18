package com.delores.medusa.config;

import com.delores.medusa.config.interceptor.DataBaseInterceptor;
import com.delores.medusa.config.interceptor.JwtAuthInterceptor;
import com.delores.medusa.config.interceptor.JwtRedisAuthInterceptor;
import com.delores.medusa.config.interceptor.RedisAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author William
 * @date 5/4/21 1:18 PM
 * @description
 */
@Configuration
@EnableAsync
public class WebMvcConfig implements WebMvcConfigurer {


    //通过手动javaConfig的方式显示注入bean
    @Bean
    public DataBaseInterceptor dataBaseInterceptor() {
        return new DataBaseInterceptor();
    }

    @Bean
    public RedisAuthInterceptor redisAuthInterceptor() {
        return new RedisAuthInterceptor();
    }

    @Bean
    public JwtAuthInterceptor jwtAuthInterceptor() {
        return new JwtAuthInterceptor();
    }

    @Bean
    public JwtRedisAuthInterceptor jwtRedisAuthInterceptor() {
        return new JwtRedisAuthInterceptor();
    }

//    /**
//     * 通过拦截器来实现认证
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        final String[] excludePatterns = new String[]{"/token/unauth", "/hello", "/db/login", "/redis/login", "/jwt/login", "/jwtRedis/login"};
////        registry.addInterceptor(dataBaseInterceptor())
////        registry.addInterceptor(redisAuthInterceptor())
////        registry.addInterceptor(jwtAuthInterceptor())
//        registry.addInterceptor(jwtRedisAuthInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns(excludePatterns);
//    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    // 设置访问静态资源
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }

//    // 跨域设置
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedMethods("*");
//    }
}
