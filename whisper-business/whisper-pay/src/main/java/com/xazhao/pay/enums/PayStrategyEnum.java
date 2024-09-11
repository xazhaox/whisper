package com.xazhao.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 策略枚举，第三种方式获取所有的支付策略，将所有的支付平台（具体的策略实现）定义到这个枚举类中<br/>
 * 缺点：每次接入一个新的支付平台就需要到这个策略枚举中新增一个枚举类型，每次接入都需要改动这个枚举类
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Getter
@AllArgsConstructor
public enum PayStrategyEnum {

    /**
     * 支付宝
     */
    ALIPAY("Alipay", "Alipay", "支付宝"),

    /**
     * 微信支付
     */
    WECHAT("WeChat", "WeChat", "微信支付");

    /**
     * 支付平台Code，Map的key（前后端约定Map的key）
     */
    private final String code;

    /**
     * 具体的策略实现（策略类）Bean名称，如：@Component("WeChat")声明的Bean，WeChat就是className
     */
    private final String className;

    /**
     * 支付平台中文名称
     */
    private final String desc;

    public static String getStrategyClass(String code) {
        for (PayStrategyEnum e : PayStrategyEnum.values()) {
            if (e.code.equals(code)) {
                return e.className;
            }
        }
        return null;
    }
}
