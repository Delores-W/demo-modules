package com.delores.aop.service;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author William
 * @date 4/6/21 6:22 PM
 * @description
 */
@Configuration
@ComponentScan("com.delores.aop.service")
//@EnableTransactionManagement
public class AppConfig {

}
