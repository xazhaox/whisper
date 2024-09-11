package com.xazhao.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description Created on 2024/08/27.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BreakTest {

    @Test
    public void ifBreakTest() {

        log.info("Start testing");

        Boolean isBool = Boolean.FALSE;

        isFlag: if (isBool.equals(Boolean.TRUE)) {
            log.info("进入if");
        } else {

            if (isBool) {
                log.info("跳出if以下代码不执行");
                break isFlag;
            }
            log.info("正式业务.");
        }

        log.info("正常代码");

    }
}
