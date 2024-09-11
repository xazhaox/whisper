package com.xazhao.gateway.utils;

import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Description Created on 2024/08/07.
 * @Author Zhao.An
 */

@Slf4j
public class WebUtils {

    private static String[] headers = {
            "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"
    };

    /**
     * 获得请求匹配的 Route 路由
     *
     * @param exchange 请求
     * @return 路由
     */
    public static Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }

    /**
     * 获得客户端 IP
     * <p>
     * 参考 {@link ServletUtil#getClientIP} 的 getClientIP 方法
     *
     * @param exchange         请求
     * @param otherHeaderNames 其它 header 名字的数组
     * @return 客户端 IP
     */
    public static String getClientIp(ServerWebExchange exchange, String... otherHeaderNames) {
        if (ArrayUtil.isNotEmpty(otherHeaderNames)) {
            headers = ArrayUtil.addAll(headers, otherHeaderNames);
        }

        // 方式一：通过 header 获取
        String ip;
        for (String header : headers) {
            ip = exchange.getRequest().getHeaders().getFirst(header);
            if (!NetUtil.isUnknown(ip)) {
                return NetUtil.getMultistageReverseProxyIp(ip);
            }
        }

        // 方式二：通过 remoteAddress 获取
        if (exchange.getRequest().getRemoteAddress() == null) {
            return null;
        }
        ip = exchange.getRequest().getRemoteAddress().getHostString();

        return NetUtil.getMultistageReverseProxyIp(ip);
    }
}
