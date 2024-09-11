package com.xazhao.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description Created on 2024/08/10.
 * @Author Zhao.An
 */

@Slf4j
@MapperScan(basePackages = "com.xazhao.**.mapper")
@SpringBootApplication
@EnableDiscoveryClient
public class ElasticsearchApplication {

    public static void main(String[] args) {

        SpringApplication.run(ElasticsearchApplication.class, args);
        log.info("(♥◠‿◠)ﾉﾞ  whisper-elasticsearch启动成功...   ლ(´ڡ`ლ)ﾞ");
    }
}
