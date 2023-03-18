package com.delores.medusa.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.delores.medusa.model.enums.UserState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author William
 * @date 4/12/21 1:13 AM
 * @description
 */
@Data
@TableName(schema = "medusa") //for mybatis plus schema

@Entity
@Table(name = "user")
// hibernate 懒加载的时候 转json会报错
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class User implements Serializable {

    private static final long serialVersionUID = 579172728484445771L;

    @Id
    private Long id;
    private UserState state;
    private String name;
    private String mobile;
    private String password;
    private Date created;
}
