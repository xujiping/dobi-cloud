package com.cloud.fast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 异步配置
 *
 * @Author: xujiping
 * @Date: 2019年8月27日 0027 下午 02:16:27
 * @Version 1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    private static int CORE_POOL_SIZE = 10;
    private static int MAX_POOL_SIZE = 200;
    private int QUEUE_CAPACITY = 10;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.initialize();
        return executor;
    }
}
