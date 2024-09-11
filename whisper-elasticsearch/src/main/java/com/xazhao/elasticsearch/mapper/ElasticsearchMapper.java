package com.xazhao.elasticsearch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

@Mapper
public interface ElasticsearchMapper {

    /**
     * 根据用户ID查询
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    Map<String, Object> getUserOne(@Param("userId") String userId);
}
