package com.xazhao.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pay {

    /**
     * 支付平台
     */
    private String payType;

    /**
     * 商品ID
     */
    private Long productId;
}
