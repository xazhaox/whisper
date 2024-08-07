package com.xazhao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description Created on 2024/08/05.
 * @Author xaZhao
 */

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class SystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(SystemApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  tripartite-pay启动成功...   ლ(´ڡ`ლ)ﾞ");
    }
}
