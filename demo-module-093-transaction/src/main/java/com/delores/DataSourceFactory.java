package com.delores;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author William
 * @date 4/7/21 11:08 AM
 * @description
 */
public class DataSourceFactory {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static
    {
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUsername("SA");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:hsqldb:mem:bank");
    }

    public static DataSource createDataSource()
    {
        return dataSource;
    }
}
