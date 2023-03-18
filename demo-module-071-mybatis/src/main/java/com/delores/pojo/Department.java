package com.delores.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author William
 * @date 3/28/21 4:16 PM
 * @description
 */
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = 4455997278736316030L;
    private Long id;
    private String name;
    private List<Employee> employees;
}
