package com.delores.medusa.model;

import lombok.Data;

import java.util.Date;

/**
 * @author William
 * @date 4/14/21 3:21 PM
 * @description pojo for hibernate
 */
@Data
public class Employee {

    private Long id;
    private String name;
    private Date created;
}
