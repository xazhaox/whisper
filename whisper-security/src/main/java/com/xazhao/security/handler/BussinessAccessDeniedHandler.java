package com.xazhao.security.handler;

import cn.hutool.json.JSONUtil;
import com.xazhao.core.response.InvokeResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 解决认证过的用户访问无权限资源时的异常
 *
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

@Component
public class BussinessAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        InvokeResult<Object> accessResult = InvokeResult.failure("Need Authorities");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(accessResult));
    }
}
