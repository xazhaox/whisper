package com.xazhao.elasticsearch.service;

import com.xazhao.core.response.InvokeResult;

import java.util.Map;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

public interface ElasticsearchService {

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    InvokeResult<Map<String, Object>> getUserOne(String userId);
}
