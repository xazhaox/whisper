package com.xazhao.pay.strategy;

import com.xazhao.core.entity.InvokeResult;
import com.xazhao.pay.entity.Pay;

import java.util.Map;

/**
 * 支付策略接口
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

public interface PayStrategy {

    /**
     * 支付平台实现各自的支付功能
     *
     * @param pay       支付数据
     * @param resultMap 返回结果
     * @return 支付结果
     */
    InvokeResult<Map<String, Object>> pay(Pay pay, Map<String, Object> resultMap);
}
