package com.xazhao.pay.strategy.impl;

import com.xazhao.core.entity.InvokeResult;
import com.xazhao.pay.entity.Pay;
import com.xazhao.pay.strategy.PayStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 工商银行
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@Component("ICBC")
public class IndustrialCommercialBankServiceImpl implements PayStrategy {

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
}
