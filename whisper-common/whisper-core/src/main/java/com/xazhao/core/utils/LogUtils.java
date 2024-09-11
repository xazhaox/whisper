package com.xazhao.core.utils;

/**
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

public class LogUtils {

    public static String getBlock(Object msg) {
        if (null == msg) {
            msg = "";
        }
        return "[" + msg + "]";
    }
}
