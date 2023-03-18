package com.delores.medusa.model;

import com.delores.medusa.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author William
 * @date 5/17/21 6:00 PM
 * @description
 */
public class TestAutoWired {

    @Autowired
    private RedisService redisService;

    public void test() {
        System.out.println(redisService.get("user"));
    }
}
