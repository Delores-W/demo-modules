package com.delores.medusa.controller;

import com.delores.medusa.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * @author William
 * @date 4/12/21 12:40 PM
 * @description
 */
@Slf4j
@RestController
public class JdbcController {

    @Autowired
    private DataSource dataSource;
    // HikariPool

    @GetMapping("/jdbc")
    public String jdbc() throws SQLException {
        log.info("原生JDBC");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from medusa.user");
            if(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Date created = resultSet.getDate("created");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setCreated(created);
                log.info(user.toString());
            }
        }
        return "test jdbc";
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/jdbcTemplate/users")
    public String jdbcTemplateTest() {
        log.info("JDBC Template");
        List<User> list = jdbcTemplate.query("select * from medusa.user", new BeanPropertyRowMapper<>(User.class));
        for(User user : list) {
            log.info(user.toString());
        }
        return "test jdbc template";
    }
}
