package com.xazhao.pay.exception;

import com.xazhao.core.exception.ErrorCode;
import com.xazhao.core.exception.ServiceException;

/**
 * @Description Created on 2024/08/28.
 * @Author Zhao.An
 */

public class PayException extends ServiceException {

    public PayException(ErrorCode errorCode) {
        super(errorCode);
    }

    public PayException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public PayException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public PayException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public PayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}