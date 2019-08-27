package com.cloud.fast.job;

import com.cloud.fast.service.SignActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: xujiping
 * @Date: 2019年8月27日 0027 下午 02:14:15
 * @Version 1.0
 */
@Component
@Slf4j
public class SignActivityJob {

    @Autowired private SignActivityService signActivityService;

    @Async
    @Scheduled(cron = "* 0/10 * * * *")
    public void test(){
        log.debug("》》》更新活动表状态任务开始");
        signActivityService.updateExpired();
        log.debug("《《《更新活动表状态任务结束");
    }

}
