package com.xazhao.security.handler;

import cn.hutool.json.JSONUtil;
import com.xazhao.core.response.InvokeResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录成功的处理器
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        InvokeResult<Object> authenticationSuccess = InvokeResult.success("登录成功");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(authenticationSuccess));
    }
}
