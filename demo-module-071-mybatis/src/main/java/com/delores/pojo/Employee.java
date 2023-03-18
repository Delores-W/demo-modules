package com.delores.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author William
 * @date 3/26/21 5:57 PM
 * @description
 */
@Data
@Alias("emp")
public class Employee {
    private Long id;
    private String lastName;
    private char gender;
    private String email;
    private Department dept;
}
