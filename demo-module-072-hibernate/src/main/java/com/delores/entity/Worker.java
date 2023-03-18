package com.delores.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author William
 * @date 4/14/21 3:30 PM
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    private Long id;
    private String name;
    private Date created;
}
