package com.xazhao.elasticsearch.controller;

import com.xazhao.core.entity.InvokeResult;
import com.xazhao.elasticsearch.service.ElasticsearchService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description Created on 2024/08/05.
 * @Author Zhao.An
 */

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/search/user")
public class ElasticsearchController {

    @Resource
    private ElasticsearchService elasticsearchService;

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/insert")
    public InvokeResult<Map<String, Object>> selectOneUser(@RequestParam("userId") String userId) {

        return elasticsearchService.getUserOne(userId);
    }
}
