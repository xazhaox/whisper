package com.xazhao.core.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Created on 2024/08/29.
 * @Author Zhao.An
 */

@Slf4j
@Configuration
public class SpringContextHolderConfiguration {

    @Bean
    public SpringContextHolder springApplicationContext() {
        SpringContextHolder springContextHolder = new SpringContextHolder();
        log.info("SpringContextHolder creation is successful");
        return springContextHolder;
    }
}
