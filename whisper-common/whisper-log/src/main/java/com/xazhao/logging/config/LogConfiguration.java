package com.xazhao.logging.config;

import com.xazhao.logging.service.LoginLogService;
import com.xazhao.logging.service.OperationLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Created on 2024/08/30.
 * @Author Zhao.An
 */

@Configuration
public class LogConfiguration {

    @Bean
    public LoginLogService loginLogService() {
        return new LoginLogService();
    }

    @Bean
    public OperationLogService operationLogService() {
        return new OperationLogService();
    }
}
