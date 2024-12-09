package com.xazhao.auth.controller;

import com.xazhao.core.response.InvokeResult;
import com.xazhao.auth.entity.LoginParam;
import com.xazhao.auth.entity.LoginUser;
import com.xazhao.auth.service.LoginService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Created on 2024/08/20.
 * @Author Zhao.An
 */

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/auth/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 用户登录，账号登录接口
     *
     * @param loginParam 登录信息
     * @return 登录信息（Token）
     */
    @PostMapping("/account")
    public InvokeResult<LoginUser> accountLogin(@Valid @RequestBody LoginParam loginParam) {

        return loginService.accountLogin(loginParam);
    }

    /**
     * 用户登录，微信登录接口
     *
     * @param loginParam 登录信息
     * @return 登录信息（Token）
     */
    @PostMapping("/wechat")
    public InvokeResult<LoginUser> weChatLogin(@RequestBody LoginParam loginParam) {

        return loginService.weChatLogin(loginParam);
    }
}
