package com.delores.medusa_transaction.respository;

import com.delores.medusa_transaction.domain.InsuranceAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author William
 * @date 4/7/21 1:03 AM
 * @description
 */
public interface InsuranceAccountRepository extends JpaRepository<InsuranceAccount, Integer> {

    @Modifying
    @Query("update InsuranceAccount set money = money - ?2 where id = ?1")
    void deposit(Integer id, Integer money);
}
