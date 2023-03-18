package com.delores.medusa_transaction.cotroller;

import com.delores.medusa_transaction.utils.DataSourceFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author William
 * @date 4/7/21 9:18 AM
 * @description
 */
@RestController
@RequestMapping("/h2")
public class H2Controller {

    @RequestMapping("/transferSuccess")
    public void transferSuccess() throws SQLException {
        DataSource dataSource = DataSourceFactory.createDataSource();
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
    }
}
