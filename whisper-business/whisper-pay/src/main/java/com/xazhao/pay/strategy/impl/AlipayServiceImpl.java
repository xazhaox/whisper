package com.xazhao.pay.strategy.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.xazhao.core.constant.Constant;
import com.xazhao.core.entity.InvokeResult;
import com.xazhao.pay.config.AlipayClientConfiguration;
import com.xazhao.pay.config.AlipayProperties;
import com.xazhao.pay.constant.PayConstant;
import com.xazhao.pay.constant.PayConventions;
import com.xazhao.pay.entity.Pay;
import com.xazhao.pay.exception.PayErrorCode;
import com.xazhao.pay.exception.PayException;
import com.xazhao.pay.strategy.PayStrategy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Map;

/**
 * <h3>支付宝</h3> @Component("Alipay")修改了Bean的默认命名，当前Bean的名称为Alipay，也可使用@Service("Alipay")
 * <p/>
 * 个人账户，使用支付宝提供的沙箱环境进行调试
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Slf4j
@Component("Alipay")
public class AlipayServiceImpl implements PayStrategy, PayConventions {

    /**
     * Alipay支付默认金额，注意：这里以分位单位默认10分，0.1元
     */
    public static final Double TOTAL_AMOUNT = 10D;

    @Resource
    private AlipayProperties alipay;

    /**
     * AlipayClient相关信息（初始化）已经在Spring初始化的时候船创建完成{@link AlipayClientConfiguration#alipayClient()}，可直接注入使用<p/>
     * 若没有在Spring初始化时创建，需要在调用时在自行创建
     *
     * @see AlipayClientConfiguration#alipayClient()
     */
    @Resource
    private AlipayClient alipayClient;

    /**
     * 支付平台实现支付功能
     *
     * @param pay       支付数据
     * @param resultMap 返回结果
     * @return 支付结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public InvokeResult<Map<String, Object>> pay(Pay pay, Map<String, Object> resultMap) {

        log.info("{} 支付.", pay.getPayType());

        String formActionUrl = this.tradePagePay(pay);
        resultMap.put(ACTION_URL, formActionUrl);

        return InvokeResult.success(resultMap, pay.getPayType() + " 支付.");
    }

    /**
     * <h3>统一收单下单并支付页面接口</h3>
     * 支付宝开发平台接收到Request请求后，会生成一个Html形式的form表单，且包含自动提交的脚本，将form表单字符串返回给前端<p/>
     * 前端接收到会自动提交脚本，并进行表的提交，此时表单会自动赐教到action属性所指向的支付宝开放平台中，给用户提供一个支付页面<p/>
     *
     * @param pay 支付数据
     * @return 支付页面Url
     */
    public String tradePagePay(Pay pay) {

        try {
            Long productId = pay.getProductId();
            if (null == pay.getProductId()) {
                productId = IdUtil.getSnowflakeNextId();
            }
            // 生成订单
            log.info("{} 支付，模拟生成订单号，订单号为：{}.", pay.getPayType(), productId);

            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            // 异步接收地址，仅支持http/https，公网可访问
            request.setNotifyUrl("");
            // 同步跳转地址，仅支持http/https
            request.setReturnUrl("");

            // 组装业务方法的必选参数。官方解释：请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递
            JSONObject bizContent = new JSONObject();

            // 商户订单号，商家自定义，保持唯一性
            bizContent.put("out_trade_no", pay.getProductId());

            // total_amount官方解释：订单总金额，单位为元，精确到小数点后两位，取值范围为 [0.01,100000000]。金额不能为0
            BigDecimal totalAmount = new BigDecimal(
                    String.valueOf(TOTAL_AMOUNT)).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            // 支付金额，最小值0.01元
            bizContent.put("total_amount", totalAmount);

            // 订单标题，不可使用特殊符号
            bizContent.put("subject", "安安实验室支付订单");
            // 电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
            bizContent.put("product_code", PayConstant.FAST_INSTANT_TRADE_PAY);

            // 可选参数，time_expire：订单绝对超时时间，这里配置订单5分钟内未支付则取消订单
            String timeExpire = DateUtil.format(DateUtil.offsetMinute(new Date(), 5), Constant.NORM_DATETIME_PATTERN);
            bizContent.put("time_expire", timeExpire);

            // 商品明细信息，按需传入
            JSONArray goodsDetail = new JSONArray();
            // 子订单A
            JSONObject goodsOrderA = new JSONObject();
            goodsOrderA.put("goods_id", IdUtil.getSnowflakeNextId());
            goodsOrderA.put("goods_name", "子订单A");
            goodsOrderA.put("quantity", 1);
            goodsOrderA.put("price", 0.2);
            goodsDetail.add(goodsOrderA);
            // 子订单B
            JSONObject goodsOrderB = new JSONObject();
            goodsOrderB.put("goods_id", IdUtil.getSnowflakeNextId());
            goodsOrderB.put("goods_name", "子订单B");
            goodsOrderB.put("quantity", 1);
            goodsOrderB.put("price", 0.3);
            goodsDetail.add(goodsOrderB);

            // goods_detail：订单包含的商品列表信息，json格式
            bizContent.put("goods_detail", goodsDetail);

            // 扩展信息
            JSONObject extendParams = new JSONObject();
            // 系统商编号，该参数作为系统商返佣数据提取的依据，请填写系统商签约协议的PID
            extendParams.put("sys_service_provider_id", alipay.getSellerId());
            // 官方解释，extend_params：业务扩展参数
            bizContent.put("extend_params", extendParams);

            // 设置必选参数
            request.setBizContent(bizContent.toString());

            // 返回POST请求。如果需要返回GET请求将POST修改位GET
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request, "POST");
            // 获取重定向到支付页面的form表单，无需还原被转义的HTML特殊字符，如；&lt转义为<
            String redirectionUrl = response.getBody();

            // 调用成功返回重定向到支付页面的form表单
            if (response.isSuccess()) {
                log.info("{} 统一收单下单并支付页面接口调用成功.", pay.getPayType());
                return redirectionUrl;

            } else {
                throw new PayException(pay.getPayType() + " 调用统一收单下单并支付页面接口出现异常.", PayErrorCode.UNIFICATION_PAY_INTERFACE_ERROR);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new PayException(pay.getPayType() + " 支付出现异常.", PayErrorCode.PAY_ERROR);
        }
    }
}
