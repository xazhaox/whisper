package com.xazhao.auth.service;

import com.xazhao.core.entity.InvokeResult;
import com.xazhao.auth.entity.LoginParam;
import com.xazhao.auth.entity.LoginUser;

/**
 * @Description Created on 2024/08/20.
 * @Author Zhao.An
 */

public interface LoginService {

    /**
     * 用户登录，账号登录接口
     *
     * @param loginParam 登录信息
     * @return 登录信息（Token）
     */
    InvokeResult<LoginUser> accountLogin(LoginParam loginParam);

    /**
     * 用户登录，微信登录接口
     *
     * @param loginParam 登录信息
     * @return 登录信息（Token）
     */
    InvokeResult<LoginUser> weChatLogin(LoginParam loginParam);
}
