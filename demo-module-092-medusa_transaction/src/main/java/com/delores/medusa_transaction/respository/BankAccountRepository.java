package com.delores.medusa_transaction.respository;

import com.delores.medusa_transaction.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author William
 * @date 4/7/21 12:50 AM
 * @description
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    @Modifying
    @Query("update BankAccount set money = money + ?2 where id = ?1")
    void withdraw(Integer id, Integer money);
}
