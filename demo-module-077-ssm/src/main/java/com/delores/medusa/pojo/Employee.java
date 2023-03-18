package com.delores.medusa.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * @author William
 * @date 3/26/21 5:57 PM
 * @description
 */
@Data
public class Employee {
    private Long id;
    private String lastName;
    private char gender;
    private String email;
}
