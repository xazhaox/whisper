package com.xazhao.logging.async;

import cn.hutool.core.util.IdUtil;
import cn.hutool.http.useragent.UserAgent;
import com.xazhao.core.context.SpringApplicationContext;
import com.xazhao.core.utils.AddressUtils;
import com.xazhao.logging.constant.Login;
import com.xazhao.logging.service.LoginLog;
import com.xazhao.logging.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 异步执行保存登录日志
 *
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Slf4j
public class LogTimerTask extends TimerTask {

    private final UserAgent userAgent;

    private final String ip;

    private final String userCode;

    private final String username;

    private final String status;

    private final String message;

    private final Object[] args;

    public LogTimerTask(UserAgent userAgent, String ip, String userCode, String username, String status, String message, Object... args) {
        this.userAgent = userAgent;
        this.ip = ip;
        this.userCode = userCode;
        this.username = username;
        this.status = status;
        this.message = message;
        this.args = args;
    }

    @Override
    public void run() {
        String address = AddressUtils.getIpAddress(ip);
        consoleLog(address, ip, username, status, message, args);
        // 获取客户端操作系统
        String osName = userAgent.getOs().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        LoginLog loginLog = setSysLoginLog(address, osName, browser);
        // 日志状态
        if (Login.LOGIN_SUCCESS.equals(status) || Login.LOGOUT.equals(status)) {
            loginLog.setStatus(Login.SUCCESS);
        } else if (Login.LOGIN_FAIL.equals(status)) {
            loginLog.setStatus(Login.FAIL);
        }
        // 插入数据
        LoginLogService loginLogService = SpringApplicationContext.getBean(LoginLogService.class);
        loginLogService.insertLoginInfo(loginLog);
    }

    private LoginLog setSysLoginLog(String address, String osName, String browser) {
        // 封装对象
        return LoginLog.builder()
                .loginId(IdUtil.getSnowflakeNextId())
                .userCode(userCode)
                .userName(username)
                .ipAddress(ip)
                .loginLocation(address)
                .browser(browser)
                .os(osName)
                .msg(message)
                .engine(userAgent.getEngine().getName())
                .mobile(userAgent.isMobile() ? Login.MOBILE_YES : Login.MOBILE_NO)
                .platform(userAgent.getPlatform().getName())
                .build();
    }

    private static void consoleLog(String address, String ip, String username, String status, String message, Object... args) {
        String loginLog = "Ip：" + ip + "，地址：" + address + "，用户：" + username + "，登录状态：" + status + "，异常日志：" + message;
        log.info(loginLog, args);
    }
}
