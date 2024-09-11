package com.xazhao.logging.factory;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.xazhao.logging.async.LogTimerTask;
import com.xazhao.core.utils.IpUtils;
import com.xazhao.core.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步工厂
 *
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

@Slf4j
public class AsyncFactory {

    private static final String USER_AGENT = "User-Agent";

    /**
     * 记录登录信息
     *
     * @param userCode 用户代码
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask checkLoginLog(
            final String userCode, final String username, final String status, final String message, final Object... args) {
        HttpServletRequest request = ServletUtils.getRequest();
        if (null == request) {
            return null;
        }
        String header = request.getHeader(USER_AGENT);
        // 解析用户代理字符串
        final UserAgent userAgent = UserAgentUtil.parse(header);
        // 获取Ip
        final String ip = IpUtils.getIpAddr(request);
        // 创建计时器任务
        return new LogTimerTask(userAgent, ip, userCode, username, status, message, args);
    }
}
