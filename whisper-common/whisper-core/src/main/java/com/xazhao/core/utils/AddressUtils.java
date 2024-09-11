package com.xazhao.core.utils;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.xazhao.core.config.CommonProperties;
import com.xazhao.core.constant.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 获取地址类
 *
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

@Slf4j
public class AddressUtils {

    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 未知地址
     */
    private static final String UNKNOWN = "XX XX";

    public static String getIpAddress(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (CommonProperties.getAddressEnabled()) {
            try {
                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Charsets.GBK);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return UNKNOWN;
                }
                Gson gson = new Gson();
                JsonObject obj = gson.fromJson(rspStr, JsonObject.class);
                String region = obj.get("pro").getAsString();
                String city = obj.get("city").getAsString();
                return String.format("%s %s", region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return UNKNOWN;
    }
}
