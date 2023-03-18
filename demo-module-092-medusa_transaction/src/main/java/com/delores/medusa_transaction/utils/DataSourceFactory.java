package com.delores.medusa_transaction.utils;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

/**
 * @author William
 * @date 4/7/21 9:09 AM
 * @description
 */
public class DataSourceFactory {

    private static final JdbcDataSource dataSource = new JdbcDataSource();

    static
    {
        dataSource.setUser("SA");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:mem:bank");
    }

    public static DataSource createDataSource()
    {
        return dataSource;
    }
}
