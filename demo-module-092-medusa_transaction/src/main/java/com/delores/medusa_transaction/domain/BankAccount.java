package com.delores.medusa_transaction.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author William
 * @date 4/7/21 12:20 AM
 * @description
 */
@Entity
public class BankAccount {

    @Id
    private Integer id;
    private Integer money;
}
