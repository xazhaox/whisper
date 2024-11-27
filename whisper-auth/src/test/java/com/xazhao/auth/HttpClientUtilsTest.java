package com.xazhao.auth;

import com.xazhao.core.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

/**
 * @Description Created on 2024/10/23.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpClientUtilsTest {

    @Test
    public void httpPostTest() {

        try {
            Map<String, Object> returnMap = HttpClientUtils.httpPost(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
