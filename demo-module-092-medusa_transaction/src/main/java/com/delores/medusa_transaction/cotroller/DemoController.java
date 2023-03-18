package com.delores.medusa_transaction.cotroller;

import com.delores.medusa_transaction.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 4/7/21 12:46 AM
 * @description
 */
@RestController
public class DemoController {

    @Autowired
    private TransferService transferService;

    @RequestMapping("/transferSuccess")
    public void transferSuccess() {
        transferService.transferSuccess(1, 1, 200);
    }

    @RequestMapping("/transferError")
    public void transferError() {
        transferService.transferError(1, 1, 200);
    }

    @RequestMapping("/transferCatchError")
    public void transferCatchError() {
        transferService.transferCatchError(1, 1, 200);
    }
}
