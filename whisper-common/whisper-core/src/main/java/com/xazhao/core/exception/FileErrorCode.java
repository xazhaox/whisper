package com.xazhao.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件通用错误码
 *
 * @Description Created on 2024/10/28.
 * @Author Zhao.An
 */

@Getter
@AllArgsConstructor
public enum FileErrorCode implements ErrorCode {

    /**
     * 通知保存失败
     */
    DOWNLOAD_FAILED("DOWNLOAD_FAILED", "文件下载失败");

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
