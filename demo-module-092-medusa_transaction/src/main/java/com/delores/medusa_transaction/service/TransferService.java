package com.delores.medusa_transaction.service;

import com.delores.medusa_transaction.respository.BankAccountRepository;
import com.delores.medusa_transaction.respository.InsuranceAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author William
 * @date 4/7/21 12:59 AM
 * @description
 */
@Service
@Transactional(propagation = Propagation.NESTED)
public class TransferService{

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private InsuranceAccountRepository insuranceAccountRepository;

    public void transferSuccess(Integer fromId, Integer toId, Integer money) {
        bankAccountRepository.withdraw(fromId, money);
        insuranceAccountRepository.deposit(toId, money);
    }

    public void transferError(Integer fromId, Integer toId, Integer money) {
        bankAccountRepository.withdraw(fromId, money);
        int x = 1/0;
        insuranceAccountRepository.deposit(toId, money);
    }

    public void transferCatchError(Integer fromId, Integer toId, Integer money) {
        try {
            bankAccountRepository.withdraw(fromId, money);
            insuranceAccountRepository.deposit(toId, money);
            int x = 1/0;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
