package com.xazhao.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * The type Common properties.
 *
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

@Component
@ConfigurationProperties(prefix = CommonProperties.PREFIX)
public class CommonProperties {

    /**
     * The constant PREFIX.
     */
    public static final String PREFIX = "com.xazhao";

    /**
     * 获取地址开关
     */
    @Getter
    @Setter
    private static Boolean addressEnabled;
}
