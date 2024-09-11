package com.xazhao.system.mapper;

import com.xazhao.system.dto.UsersDTO;
import com.xazhao.system.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

@Mapper
public interface UsersMapper {

    /**
     * 根据用户代码查询
     *
     * @param nickCode 用户代码
     * @return 用户信息
     */
    Users getUserOne(@Param("nickCode") String nickCode);


    /**
     * 分页查询用户
     *
     * @param users 查询条件
     * @return 用户信息数据集
     */
    List<Users> getUserList(@Param("users") UsersDTO users);
}
