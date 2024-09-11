package com.xazhao.logging.service;

import com.xazhao.logging.mapper.LoginLogMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Slf4j
@Component
public class LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    /**
     * 插入登录日志
     *
     * @param loginLog 登录信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertLoginInfo(LoginLog loginLog) {

        try {
            loginLogMapper.insertLoginInfo(loginLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
