package com.xazhao.pay;

import cn.hutool.json.JSONUtil;
import com.xazhao.core.response.InvokeResult;
import com.xazhao.pay.entity.Pay;
import com.xazhao.pay.enums.PayStrategyEnum;
import com.xazhao.pay.service.TripartitePayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PayTest {

    @Resource
    private TripartitePayService tripartitePayService;

    @Test
    public void tripartitePayTest() {

        Pay pay = new Pay();
        pay.setPayType(PayStrategyEnum.ALIPAY.getCode());

        InvokeResult<Map<String, Object>> result = tripartitePayService.tripartitePayUnifiedInterface(pay);

        log.info(JSONUtil.toJsonStr(result));
    }
}
