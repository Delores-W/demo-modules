package com.delores.order_service.controller;

import com.delores.base.service.RedisService;
import com.delores.base.utils.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author William
 * @date 5/18/21 6:50 PM
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/orderService")
public class OrderController {

    @Autowired
    private RedisService redisService;

    @GetMapping("test")
    public String test() {
        System.out.println("invoke order service");
        return "test service";
    }

    @GetMapping("redis")
    public void redisLock() {
//        boolean lock = redisService.setIfAbsent("10001", "lock", 1000L, TimeUnit.SECONDS);
        boolean lock = redisService.lock("123456");
        if (lock) {
            System.out.println("开始执行业务");
            boolean unlock = redisService.unlock("123456");
            System.out.println("解锁成功？-- " + unlock);
        } else {
            System.out.println("锁已经被占用，请等待或重试");
        }
    }

    int count = 0;

    @GetMapping("redisLock")
    public void redisLockTest() throws InterruptedException {

        // Variable used in lambda expression should be final or effectively final
//        int count = 0;

        int clientCount = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        long start = System.currentTimeMillis();
        SnowFlake snowFlake = new SnowFlake(1,2);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < clientCount; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
                Long id = snowFlake.nextId();
                log.info(id.toString());
                try {
                    redisService.lock(id.toString());
                    count++;
                    countDownLatch.countDown();
                } finally {
                    redisService.unlock(id.toString());
                }
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        log.info("执行线程数:{},总耗时:{},count数为:{}", clientCount, end - start, count);

    }

//    @Async("taskExecutor")
//    public int ms(int count, CountDownLatch countDownLatch) {
//        Long id = new SnowFlake(3, 3).nextId();
//        try {
//            redisService.lock(id.toString());
//            count++;
//            countDownLatch.countDown();
//            return count;
//        } finally {
//            redisService.unlock(id.toString());
//        }
//    }

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("redissonLock")
    public void redissonLock() throws InterruptedException {
        RLock redis_lock = redissonClient.getLock("redis_lock");
        redis_lock.lock();
        boolean locked = redis_lock.isLocked();
        if (locked) {
            Thread.sleep(50000);
            redis_lock.unlock();
        } else {
            System.out.println("加锁失败");
        }

    }
}
