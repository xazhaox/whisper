package com.xazhao.datasource.wrapper;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

/**
 * 重写Map的包装器将Map的key全部转换为小写
 *
 * @Description Created on 2024/11/27.
 * @Author Zhao.An
 */

public class CustomMapWrapper extends MapWrapper {

    public CustomMapWrapper(MetaObject metaObject, Map<String, Object> map) {
        super(metaObject, map);
    }

    /**
     * 这里将Map的Key改为小写，可扩展大写、驼峰等
     *
     * @param name                Key
     * @param useCamelCaseMapping 使用驼峰映射
     * @return Key
     */
    @Override
    public String findProperty(String name, boolean useCamelCaseMapping) {

        // 返回字段转小写
        return name == null ? "" : name.toLowerCase();
    }
}
