package com.xazhao.core.concurrent;

/**
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

public interface ThreadPoolParameter {

    /**
     * 核心线程池大小
     */
    Integer CORE_POOL_SIZE = 64;

    /**
     * 最大可创建的线程数
     */
    Integer MAX_POOL_SIZE = 256;

    /**
     * 队列最大长度
     */
    Integer QUEUE_CAPACITY = 1024;

    /**
     * 线程池维护线程所允许的空闲时间，这里设置5分钟，单位为秒
     */
    Integer KEEP_ALIVE_SECONDS = 300;

    /**
     * 同步执行调度器的执行线程名。
     */
    String THREAD_NAME_PATTERN = "async - %d";
}
