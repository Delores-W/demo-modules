package com.delores.acl_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author William
 * @date 5/14/21 3:19 PM
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.delores")
@MapperScan("com.delores.acl_service.mapper")
public class ServiceAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }

}
