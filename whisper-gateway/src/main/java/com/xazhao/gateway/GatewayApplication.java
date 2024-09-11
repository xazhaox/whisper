package com.xazhao.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description Created on 2024/08/07.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {

        /* 若是配置了Spring Cloud的Gateway网关每次在访问服务时端口号统一为Gateway服务的端口号49082，由Gateway进行统一转发到相应的服务上 */

        SpringApplication.run(GatewayApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  whisper-gateway启动成功...   ლ(´ڡ`ლ)ﾞ");
    }
}
