package com.delores.controller;

import com.delores.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 4/7/21 11:53 PM
 * @description
 */
@RestController
public class HelloController {

    @Autowired
    private PayService payService;

    @RequestMapping("/")
    public String hello() {
        return "Hello Delores";
    }

    @RequestMapping("/pay")
    public String pay() {
        System.out.println("pay begin");
        payService.pay();
        System.out.println("pay end");
        return "pay success";
    }
}
