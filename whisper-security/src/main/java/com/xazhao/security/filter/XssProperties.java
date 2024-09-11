package com.xazhao.security.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description Created on 2024/08/19.
 * @Author Zhao.An
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = XssProperties.PREFIX)
public class XssProperties {

    public static final String PREFIX = "xss";

    /**
     * 过滤开关
     */
    private Boolean enabled;

    /**
     * 排除链接（多个用逗号分隔）
     */
    private String[] excludesUrl;

    /**
     * 匹配链接
     */
    private String[] patternsUrl;
}
