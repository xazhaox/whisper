package com.xazhao.pay.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 支付宝电脑网页支付相关参数
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@Data
@ConfigurationProperties(prefix = AlipayProperties.PREFIX)
public class AlipayProperties {

    public static final String PREFIX = "pay.alipay";

    /**
     * appID
     */
    private String appId;

    /**
     * PID（收单账号）
     */
    private String sellerId;

    /**
     * 商户私钥（应用私钥）
     */
    private String merchantPrivateKey;

    /**
     * 支付宝公钥
     */
    private String alipayPublicKey;

    /**
     * 支付宝网关地址，沙箱环境（与正式环境网关地址不同）
     */
    private String gatewayUrl;

    /**
     * 对称密钥，接口内容加密密钥
     */
    private String contentKey;
}
