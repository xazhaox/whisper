package com.xazhao.auth;

import com.xazhao.auth.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description Created on 2024/11/27.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisMapLowerCaseTest {

    @Resource
    private LoginService loginService;

    @Test
    public void mybatisMapLowerCaseTest() {

        loginService.pageMapQuery();
    }
}
