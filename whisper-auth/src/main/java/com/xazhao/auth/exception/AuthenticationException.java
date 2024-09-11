package com.xazhao.auth.exception;

import com.xazhao.core.exception.ErrorCode;
import com.xazhao.core.exception.SystemException;

/**
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

public class AuthenticationException extends SystemException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public AuthenticationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public AuthenticationException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public AuthenticationException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public AuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
