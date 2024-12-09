package com.xazhao.system.controller;

import com.github.pagehelper.PageInfo;
import com.xazhao.core.response.InvokeResult;
import com.xazhao.core.request.PageParameter;
import com.xazhao.system.dto.UsersDTO;
import com.xazhao.system.entity.Users;
import com.xazhao.system.service.UsersService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Created on 2024/08/05.
 * @Author Zhao.An
 */

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/system/user")
public class UsersController {

    @Resource
    private UsersService usersService;

    /**
     * 根据用户代码查询
     *
     * @param nickCode 用户代码
     * @return 用户信息
     */
    @GetMapping("/getOne")
    public InvokeResult<Users> selectOneUser(@RequestParam("nickCode") String nickCode) {

        return usersService.getUserOne(nickCode);
    }

    /**
     * 分页查询用户
     *
     * @param pageParameter 分页参数
     * @param users         查询条件
     * @return 用户信息数据集
     */
    @GetMapping("/pageList")
    public InvokeResult<PageInfo<Users>> selectUserList(PageParameter pageParameter, UsersDTO users) {

        return usersService.selectUserList(pageParameter, users);
    }


    @RequestMapping("/xss")
    public String selectUserList(@RequestParam("xss") String xss) {

        // http://127.0.0.1:49082/system/user/xss?xss=<script>alert('存储型：xss')</script>
        return xss;
    }
}
