package com.delores.step1_failure;

import com.delores.BankService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class FailureBankService implements BankService {

    private FailureBankDao failureBankDao;
    private FailureInsuranceDao failureInsuranceDao;
    private DataSource dataSource;

    public FailureBankService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void transfer(int fromId, int toId, int amount) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            failureBankDao.withdraw(fromId, amount);
            failureInsuranceDao.deposit(toId, amount);

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

    public void setFailureBankDao(FailureBankDao failureBankDao) {
        this.failureBankDao = failureBankDao;
    }

    public void setFailureInsuranceDao(FailureInsuranceDao failureInsuranceDao) {
        this.failureInsuranceDao = failureInsuranceDao;
    }
}
