package com.xazhao.auth;

import com.xazhao.core.utils.DigestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description Created on 2024/08/29.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class DigestTest {

    @Test
    public void digestTest() {

        // 加密
        String encrypt = DigestUtils.encrypt("W010001024");
        System.out.println(encrypt);

        // 解密
        String decrypt = DigestUtils.decrypt(encrypt);
        System.out.println(decrypt);

    }
}
