package com.xazhao.core.utils;

import cn.hutool.core.convert.Convert;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

/**
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

@Slf4j
public class ServletUtils {

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getParameter(name);
        } else {
            return null;
        }
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String parameter = request.getParameter(name);
            return Convert.toStr(parameter, defaultValue);
        } else {
            return null;
        }
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String parameter = request.getParameter(name);
            return Convert.toInt(parameter);
        } else {
            return null;
        }
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            String parameter = request.getParameter(name);
            return Convert.toInt(parameter, defaultValue);
        } else {
            return null;
        }
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        } else {
            return null;
        }
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getResponse();
        } else {
            return null;
        }
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return request.getSession();
        } else {
            return null;
        }
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes) {
            return (ServletRequestAttributes) attributes;
        } else {
            return null;
        }
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
