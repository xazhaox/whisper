package com.xazhao.security.handler;

import cn.hutool.json.JSONUtil;
import com.xazhao.core.response.InvokeResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录失败时的处理器
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        InvokeResult<Object> authentication = InvokeResult.failure("登录失败，请检查用户名密码");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(authentication));
    }
}
