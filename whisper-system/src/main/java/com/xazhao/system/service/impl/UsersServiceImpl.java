package com.xazhao.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.xazhao.core.entity.InvokeResult;
import com.xazhao.core.entity.PageParameter;
import com.xazhao.system.dto.UsersDTO;
import com.xazhao.system.entity.Users;
import com.xazhao.system.mapper.UsersMapper;
import com.xazhao.system.service.UsersService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 根据用户代码查询
     *
     * @param nickCode 用户代码
     * @return 用户信息
     */
    @Override
    public InvokeResult<Users> getUserOne(String nickCode) {

        Users userOne = usersMapper.getUserOne(nickCode);
        if (!ObjectUtil.isNotEmpty(userOne)) {
            return InvokeResult.failure("该用户不存在，请检查用户编码是否正确");
        }
        return InvokeResult.success(userOne, "用户查询成功！");
    }

    /**
     * 分页查询用户
     *
     * @param pageParameter 分页参数
     * @param users         查询条件
     * @return 用户信息数据集
     */
    @Override
    public InvokeResult<PageInfo<Users>> selectUserList(PageParameter pageParameter, UsersDTO users) {

        PageMethod.startPage(pageParameter.getPageNum(), pageParameter.getPageSize());
        List<Users> userList = usersMapper.getUserList(users);
        if (!CollUtil.isNotEmpty(userList)) {
            return InvokeResult.failure("为查询到结果，请检查组合条件");
        }
        // 清除分页条件
        PageMethod.clearPage();
        return InvokeResult.success(new PageInfo<>(userList), "用户查询成功！");
    }
}
