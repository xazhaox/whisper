package com.xazhao.pay.strategy.impl;

import com.xazhao.core.response.InvokeResult;
import com.xazhao.pay.entity.Pay;
import com.xazhao.pay.strategy.PayStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <h3>微信支付</h3> @Component("WeChat")修改了Bean的默认命名，当前Bean的名称为WeChat，也可使用@Service("Alipay")
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@Component("WeChat")
public class WeChatServiceImpl implements PayStrategy {

    /**
     * 支付平台实现支付功能
     *
     * @param pay       支付数据
     * @param resultMap 返回结果
     * @return 支付结果
     */
    @Override
    public InvokeResult<Map<String, Object>> pay(Pay pay, Map<String, Object> resultMap) {

        log.info("{} 支付.", pay.getPayType());

        return InvokeResult.success(resultMap, pay.getPayType() + " 支付.");
    }

    /**
     * 支付之前需要执行的
     */
    @Override
    public void beforePay() {
        log.info("支付之前执行.");
    }

    /**
     * 支付之后需要执行的
     */
    @Override
    public void afterPay() {
        log.info("支付之后执行.");
    }
}
