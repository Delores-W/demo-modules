package com.delores.entity.n21;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author William
 * @date 4/15/21 11:45 AM
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private String orderName;
    private Date created;

    private Customer customer;
}
