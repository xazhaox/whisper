package com.xazhao.pay.service;

import com.xazhao.core.entity.InvokeResult;
import com.xazhao.pay.entity.Pay;

import java.util.Map;

/**
 * 第三方支付平台统一支付入口
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

public interface TripartitePayService {

    /**
     * 第三方支付平台统一支付入口
     *
     * @param pay 支付平台
     * @return 支付结果
     */
    InvokeResult<Map<String, Object>> tripartitePayUnifiedInterface(Pay pay);
}
