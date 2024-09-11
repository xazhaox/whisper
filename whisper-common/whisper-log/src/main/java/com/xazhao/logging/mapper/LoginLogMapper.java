package com.xazhao.logging.mapper;

import com.xazhao.logging.service.LoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Mapper
public interface LoginLogMapper {

    /**
     * 插入登录日志
     *
     * @param loginLog 登录信息
     */
    void insertLoginInfo(LoginLog loginLog);
}

