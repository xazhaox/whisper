package com.xazhao.pay.exception;

import com.xazhao.core.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description Created on 2024/08/30.
 * @Author Zhao.An
 */

@Getter
@AllArgsConstructor
public enum PayErrorCode implements ErrorCode {

    /**
     * 未注册支付策略
     */
    REGISTERED_PAY_STRATEGY("REGISTERED_PAY_STRATEGY", "未注册支付策略"),

    /**
     * 获取付款策略错误
     */
    GET_PAY_POLICY_ERROR("GET_PAY_POLICY_ERROR", "获取付款策略错误"),

    /**
     * 未指定第三方支付平台
     */
    UNSPECIFIED_PAYMENT_TRIPARTITE("UNSPECIFIED_PAYMENT_TRIPARTITE", "未指定第三方支付平台"),

    /**
     * 支付出现异常
     */
    PAY_ERROR("PAY_ERROR", "支付错误"),

    /**
     * 调用统一收单下单并支付页面接口出现异常
     */
    UNIFICATION_PAY_INTERFACE_ERROR("UNIFICATION_PAY_INTERFACE_ERROR", "统一收单下单并支付接口错误");

    private String code;

    private String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
