package com.xazhao.pay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import com.xazhao.pay.constant.PayConstant;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Alipay，支付宝客户端连接工具
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayClientConfiguration {

    @Resource
    private AlipayProperties alipay;

    /**
     * <h3>初始化支付宝客户端</h3>
     * 初始化成功之后会得到{@link AlipayClient}对象，有了这个对象就可以进行接口的调用<p/>
     * AlipayClient中封装了签名和验签的完整实现，在接口调用的过程中AlipayClient会自动对远程请求添加签名，也可以自动对请求的响应进行验签
     *
     * @return AlipayClient
     * @throws AlipayApiException AlipayApiException
     * @see AlipayClient
     */
    @Bean
    public AlipayClient alipayClient() throws AlipayApiException {

        AlipayConfig alipayConfig = new AlipayConfig();

        // 设置网关地址
        alipayConfig.setServerUrl(alipay.getGatewayUrl());
        // 设置应用APPID
        alipayConfig.setAppId(alipay.getAppId());
        // 设置应用私钥
        alipayConfig.setPrivateKey(alipay.getMerchantPrivateKey());
        // 设置请求格式，固定值json
        alipayConfig.setFormat(PayConstant.FORMAT_JSON);
        // 设置字符集
        alipayConfig.setCharset(PayConstant.CHARSET_UTF8);
        // 设置支付宝公钥
        alipayConfig.setAlipayPublicKey(alipay.getAlipayPublicKey());
        // 设置签名类型
        alipayConfig.setSignType(PayConstant.SIGN_TYPE_RSA2);

        // 返回构造client
        return new DefaultAlipayClient(alipayConfig);
    }
}
