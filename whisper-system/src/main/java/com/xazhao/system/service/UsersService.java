package com.xazhao.system.service;

import com.github.pagehelper.PageInfo;
import com.xazhao.core.response.InvokeResult;
import com.xazhao.core.request.PageParameter;
import com.xazhao.system.dto.UsersDTO;
import com.xazhao.system.entity.Users;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

public interface UsersService {

    /**
     * 根据用户代码查询
     *
     * @param nickCode 用户代码
     * @return 用户信息
     */
    InvokeResult<Users> getUserOne(String nickCode);

    /**
     * 分页查询用户
     *
     * @param pageParameter 分页参数
     * @param users         查询条件
     * @return 用户信息数据集
     */
    InvokeResult<PageInfo<Users>> selectUserList(PageParameter pageParameter, UsersDTO users);
}
