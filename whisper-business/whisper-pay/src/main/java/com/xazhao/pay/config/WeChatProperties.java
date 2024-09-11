package com.xazhao.pay.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信支付电脑网页支付相关参数
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = WeChatProperties.PREFIX)
public class WeChatProperties {

    public static final String PREFIX = "pay.wechat";

    /**
     * appID
     */
    private String appId;
}
