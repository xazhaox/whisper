package com.xazhao.core.utils;

import cn.hutool.http.HTMLFilter;

/**
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

public class EscapeUtil {

    /**
     * 清除所有HTML标签，但是不删除标签内的内容
     *
     * @param content 文本
     * @return 清除标签后的文本
     */
    public static String clean(String content) {
        return new HTMLFilter().filter(content);
    }
}
