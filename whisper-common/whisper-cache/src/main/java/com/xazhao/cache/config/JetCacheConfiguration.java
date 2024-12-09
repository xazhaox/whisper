package com.xazhao.cache.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存配置
 *
 * @Description Created on 2024/12/09.
 * @Author Zhao.An
 */

@Configuration
@EnableMethodCache(basePackages = "com.xazhao")
public class JetCacheConfiguration {
}
