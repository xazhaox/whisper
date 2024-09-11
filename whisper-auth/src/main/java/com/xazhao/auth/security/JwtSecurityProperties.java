package com.xazhao.auth.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description Created on 2024/08/15.
 * @Author Zhao.An
 */

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = JwtSecurityProperties.PREFIX)
public class JwtSecurityProperties {

    public static final String PREFIX = "jwt.token";

    /**
     * Request Headers ： Authorization
     */
    private String header;

    /**
     * 令牌前缀，最后留个空格 Bearer
     */
    private String tokenStartWith;

    /**
     * Base64对该令牌进行编码
     */
    private String base64Secret;

    /**
     * 令牌过期时间 此处单位/毫秒
     */
    private Long tokenValidityInSeconds;

    /**
     * 令牌有效期小于30分钟过期则自动续期（单位：毫秒）
     */
    private Long tokenRenewalTime;

    /**
     * 返回令牌前缀
     */
    public String getTokenStartWith() {
        return tokenStartWith + " ";
    }

    private List<String> ignoreApiList;
}
