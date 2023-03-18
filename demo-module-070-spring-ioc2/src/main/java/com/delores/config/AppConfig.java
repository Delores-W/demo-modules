package com.delores.config;

import org.springframework.context.annotation.*;

/**
 * @author William
 * @date 3/25/21 4:54 PM
 * @description
 */
@Configuration
@ComponentScan("com.delores.config")
//@EnableThirdPartClass
@Import(MyFactoryBean.class)
public class AppConfig {

//    @Bean
//    public ThirdPartClass thirdPartClass() {
//        return new ThirdPartClass();
//    }
}
