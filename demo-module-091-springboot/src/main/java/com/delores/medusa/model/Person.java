package com.delores.medusa.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author William
 * @date 4/9/21 11:24 AM
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "person")
@PropertySource(value = "classpath:application.yml")
@Validated
public class Person {
    private String name;
    @Max(value = 35, message = "max age limited")
    private Integer age;
    private List<Object> list;
}
