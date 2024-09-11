package com.xazhao.pay;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@MapperScan(basePackages = "com.xazhao.**.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class TripartitePayApplication {

    public static void main(String[] args) {

        SpringApplication.run(TripartitePayApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  whisper-pay启动成功...   ლ(´ڡ`ლ)ﾞ");
    }
}
