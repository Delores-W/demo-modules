package com.delores.step2_ugly;

import com.delores.BankService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class UglyBankService implements BankService {


    private DataSource dataSource;
    private UglyBankDao uglyBankDao;
    private UglyInsuranceDao uglyInsuranceDao;

    public UglyBankService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void transfer(int fromId, int toId, int amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            uglyBankDao.withdraw(fromId, amount, connection);
            uglyInsuranceDao.deposit(toId, amount, connection);

            connection.commit();
        } catch (Exception e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try
            {
                assert connection != null;
                connection.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setUglyBankDao(UglyBankDao uglyBankDao) {
        this.uglyBankDao = uglyBankDao;
    }

    public void setUglyInsuranceDao(UglyInsuranceDao uglyInsuranceDao) {
        this.uglyInsuranceDao = uglyInsuranceDao;
    }
}
