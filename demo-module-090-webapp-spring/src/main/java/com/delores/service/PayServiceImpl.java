package com.delores.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author William
 * @date 4/8/21 11:52 AM
 * @description
 */
@Service
public class PayServiceImpl implements PayService {

    @Async("taskExecutor")
    public String pay() {
        try {
            System.out.println("开始执行pay service");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName());
            System.out.println("执行结束pay service");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success pay service";
    }
}
