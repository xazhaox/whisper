package com.xazhao.auth;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.xazhao.auth.security.JwtSecurity;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @Description Created on 2024/08/19.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtSecurityTest {

    @Resource
    private JwtSecurity jwtSecurity;

    @Test
    public void jwtSecurityTest() {

        JSON usersZhao = JSONUtil.parse(USER_ZHAO);
        Map<String, Object> userMap = BeanUtil.beanToMap(usersZhao);

        JSON parse = JSONUtil.parse(USER_AN);
        Map<String, Object> userMap1 = BeanUtil.beanToMap(parse);

        String token = jwtSecurity.generateToken(userMap);
        String token1 = jwtSecurity.generateToken(userMap1);
        log.info("\n{}", token);
        log.info("\n{}", token1);

    }

    @Test
    @SuppressWarnings("unchecked")
    public void parseJwtToken() {

        String tokenOpen = jwtSecurity.getTokenOpen(TOKEN);
        log.info("\n{}", tokenOpen);

        Claims claims = jwtSecurity.getClaims(TOKEN);

        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(claims);

        // 方法1
        Map<String, Object> userMap = (Map<String, Object>) claims.get("Auth");

        // 方法2
        String auth = JSONUtil.toJsonStr(claims.get("Auth"));
        Map<String, Object> jsonStrTomap = JSONUtil.toBean(auth, Map.class);

        Boolean tokenExpired = jwtSecurity.isTokenExpired(TOKEN);
        log.info("{}", tokenExpired);

        System.out.println();
    }

    public static final String TOKEN = "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eJxtUcFOwzAM_RXkc7UlbpqmPTEhDnCemMQlCmlYw9pkajMxNO3fcdikHUZO0XvPz8_2Cb6Shxa4wkqgwLqSqCqhWCOhgPnwQdzrZk1_bxK0vEbBKsZKWYA77i-A4BdgdUg9tCfwHbRYQPB2p4MZHVm89yYuVgGuqI1dRjdUyBgSujfz_B2nTvdm7nMcLEUla0VUcoPb9zHkAl7LpqQEjcKSKDcaP9zcHznKhY0jMdZNyX96a5KPgVIWMDkz3KfxnbaG2oZIsOSMzI_55dmTSVm8elq_vD0T0FGQ5Gg2VsBg5qSHuPVBJ__niQzFkqklxwcuWlRtmRe4HZO21Dr9L2mukjF2lDZ734tE3s-l1WF2k965n9u9uKgb0dSCDoBwPv8CzrKAng.6p21wbRGwHhjs_F1d3se_qBtnUP8FmQLIe5GF-qzrGkBTFz3qlUPO3S1v5S2jJLApS-9-27B62_8kSH9RG9mbA";

    private static final String USER_AN = """
            {
                "id": 1,
                "nick_name": "Zhao.An",
                "nick_code": "W100001",
                "password_hash": "12345678",
                "salt": null,
                "telephone": "17xxxxxx23",
                "email": "Zhao.An@126.com",
                "profile_photo_url": null,
                "certification": 1,
                "real_name": "Zhao.An",
                "id_card_no": "6103xxxxxxx",
                "user_role": null,
                "place_org": null,
                "place_dept": null,
                "duty_block": null,
                "state": "ACTIVE",
                "deleted": 0,
                "last_login_time": "2024/08/12 14:28:36",
                "lock_version": null,
                "gmt_create": "2024/8/12 14:28:39",
                "gmt_modified": "2024/08/12 14:28:42",
                "temp": null
            }
            """;

    private static final String USER_ZHAO = """
            {
                "id": 2,
                "nick_name": "Zhao.An",
                "nick_code": "W100002",
                "password_hash": "12345678",
                "salt": null,
                "telephone": "176932409823",
                "email": "Zhao.An@126.com",
                "profile_photo_url": null,
                "certification": 1,
                "real_name": "Zhao.An",
                "id_card_no": "610324xxxxx",
                "user_role": null,
                "place_org": null,
                "place_dept": null,
                "duty_block": null,
                "state": "ACTIVE",
                "deleted": 0,
                "last_login_time": "2024/08/12 14:28:36",
                "lock_version": null,
                "gmt_create": "2024/08/12 14:28:39",
                "gmt_modified": "2024/08/12 14:28:42",
                "temp": null
            }
            """;
}
