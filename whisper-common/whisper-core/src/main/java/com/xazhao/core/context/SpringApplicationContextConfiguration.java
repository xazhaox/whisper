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
public class SpringApplicationContextConfiguration {

    @Bean
    public SpringApplicationContext springApplicationContext() {
        SpringApplicationContext springContextHolder = new SpringApplicationContext();
        log.info("SpringContextHolder creation is successful");
        return springContextHolder;
    }
}
