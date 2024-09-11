package com.xazhao.elasticsearch.service.impl;

import com.xazhao.core.entity.InvokeResult;
import com.xazhao.elasticsearch.mapper.ElasticsearchMapper;
import com.xazhao.elasticsearch.service.ElasticsearchService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

@Slf4j
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Resource
    private ElasticsearchMapper elasticsearchMapper;

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @Override
    public InvokeResult<Map<String, Object>> getUserOne(String userId) {

        Map<String, Object> parameterMap = elasticsearchMapper.getUserOne(userId);
        parameterMap.put("module", "Elasticsearch");

        return InvokeResult.success(parameterMap, "用户查询成功！");
    }
}
