package com.xazhao.auth.exception;

import com.xazhao.core.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description Created on 2024/08/30.
 * @Author Zhao.An
 */

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    /**
     * 登录失败
     */
    LOGIN_FAILED("LOGIN_FAILED", "登录失败"),

    /**
     * 用户状态不可用
     */
    USER_STATUS_IS_NOT_ACTIVE("USER_STATUS_IS_NOT_ACTIVE", "用户状态不可用"),

    /**
     * 验证码错误
     */
    VERIFICATION_CODE_WRONG("VERIFICATION_CODE_WRONG", "验证码错误"),

    /**
     * 用户信息查询失败
     */
    USER_QUERY_FAILED("USER_QUERY_FAILED", "用户信息查询失败"),

    /**
     * 用户未登录
     */
    USER_NOT_LOGIN("USER_NOT_LOGIN", "用户未登录"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在");

    private String code;

    private String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
