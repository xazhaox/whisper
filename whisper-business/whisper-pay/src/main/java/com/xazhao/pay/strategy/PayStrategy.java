package com.xazhao.pay.strategy;

import com.xazhao.core.response.InvokeResult;
import com.xazhao.pay.entity.Pay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 支付策略接口
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

public interface PayStrategy {

    Logger log = LoggerFactory.getLogger(PayStrategy.class);

    /**
     * 支付平台实现各自的支付功能
     *
     * @param pay       支付数据
     * @param resultMap 返回结果
     * @return 支付结果
     */
    InvokeResult<Map<String, Object>> pay(Pay pay, Map<String, Object> resultMap);

    /**
     * 支付之前需要执行的
     */
    default void beforePay() {
        log.info("pay before.");
    }

    /**
     * 支付之后需要执行的
     */
    default void afterPay() {
        log.info("pay after.");
    }
}
