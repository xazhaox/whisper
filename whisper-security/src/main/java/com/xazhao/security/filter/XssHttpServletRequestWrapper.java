package com.xazhao.security.filter;

import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.HtmlUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Description Created on 2024/08/19.
 * @Author Zhao.An
 */

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * Html过滤
     */
    private final static HTMLFilter HTML_FILTER = new HTMLFilter();

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (StringUtils.isEmpty(value)) {
            return value;
        }
        return HtmlUtils.htmlEscape(value);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return value != null ? HtmlUtils.htmlEscape(value) : null;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                // 防xss攻击和过滤前后空格
                escapeValues[i] = HTML_FILTER.filter(values[i]).trim();
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 非json类型，直接返回
        if (!isJsonRequest()) {
            return super.getInputStream();
        }

        // 为空，直接返回
        String json = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(json)) {
            return super.getInputStream();
        }

        // xss过滤
        json = HTML_FILTER.filter(json).trim();
        final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() {
                return bis.read();
            }
        };
    }

    /**
     * 是否是Json请求
     */
    private boolean isJsonRequest() {
        String header = super.getHeader(HttpHeaders.CONTENT_TYPE);
        return StringUtils.startsWithIgnoreCase(header, MediaType.APPLICATION_JSON_VALUE);
    }
}
