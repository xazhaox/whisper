package com.xazhao.rpc.config;

import com.xazhao.rpc.facade.FacadeAspect;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rpc 配置
 *
 * @Description Created on 2024/12/09.
 * @Author Zhao.An
 */

@EnableDubbo
@Configuration
public class RpcConfiguration {

    @Bean
    public FacadeAspect facadeAspect() {

        return new FacadeAspect();
    }
}
