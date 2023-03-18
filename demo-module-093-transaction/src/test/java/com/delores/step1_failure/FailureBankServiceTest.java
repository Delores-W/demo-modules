package com.delores.step1_failure;

/**
 * @author William
 * @date 4/7/21 11:21 AM
 * @description
 */

import com.delores.BankFixture;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

public class FailureBankServiceTest extends BankFixture {

    @Test
    public void transferSuccess() throws SQLException
    {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        bankService.transfer(1111, 2222, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1200, getInsuranceAmount(2222));
    }

    @Test
    public void transferFailure() throws SQLException
    {
        FailureBankDao failureBankDao = new FailureBankDao(dataSource);
        FailureInsuranceDao failureInsuranceDao = new FailureInsuranceDao(dataSource);

        FailureBankService bankService = new FailureBankService(dataSource);
        bankService.setFailureBankDao(failureBankDao);
        bankService.setFailureInsuranceDao(failureInsuranceDao);

        int toNonExistId = 3333;
        bankService.transfer(1111, toNonExistId, 200);

        assertEquals(800, getBankAmount(1111));
        assertEquals(1000, getInsuranceAmount(2222));
    }
}
