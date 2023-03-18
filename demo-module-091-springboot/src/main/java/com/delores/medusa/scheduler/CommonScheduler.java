package com.delores.medusa.scheduler;

import com.delores.medusa.dao.mapper.AuthTokenMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author William
 * @date 2019-10-31 11:44
 * @description
 */
@Slf4j
@Component
@EnableAsync
public class CommonScheduler {

    @Autowired
    private AuthTokenMapper authTokenMapper;

    //剔除掉那些已经失效的token     cron=建议一个月一次
    @Scheduled(cron = "0 0 0 28 * ?") //每个月的 28 日 00:00:00 运行
    @Async("taskExecutor")
    public void deleteInvalidateToken() {
        try {
            log.info("--剔除掉那些已经失效的token--定时任务调度开启--");
            authTokenMapper.deleteInactiveToken();

            //交给运维-自动抽取出那些失效的token，进行转移 (etl)
        } catch (Exception e) {
            log.error("--剔除掉那些已经失效的token--发生异常：", e.fillInStackTrace());
        }
    }
}
