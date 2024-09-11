package com.xazhao.core.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建Spring线程池
 *
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

@Slf4j
@Configuration
public class SpringThreadPoolConfiguration implements ThreadPoolParameter {

    /**
     * 创建ThreadPoolTaskExecutor线程池
     *
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        // 创建线程池
        ThreadPoolTaskExecutor executorService = new ThreadPoolTaskExecutor();
        executorService.setCorePoolSize(CORE_POOL_SIZE);
        executorService.setMaxPoolSize(MAX_POOL_SIZE);
        executorService.setQueueCapacity(QUEUE_CAPACITY);
        executorService.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        // 线程池拒绝策略
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(THREAD_NAME_PATTERN).build();
        executorService.setThreadFactory(threadFactory);
        log.info("Spring ThreadPoolTaskExecutor creation is successful");
        return executorService;
    }
}
