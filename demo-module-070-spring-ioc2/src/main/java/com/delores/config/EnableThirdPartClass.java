package com.delores.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author William
 * @date 4/6/21 12:04 AM
 * @description
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ThirdPartClass.class)
public @interface EnableThirdPartClass {
    // 只要启动的时候，加入该注解，就会将@Import中的Bean加入到容器当中
}
