package com.xazhao.auth;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.xazhao.cache.core.RedisCacheStr;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description Created on 2024/08/15.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisCacheStrTest {

    @Resource
    private RedisCacheStr redisCacheStr;


    /**
     * 存储基本数据
     */
    @Test
    public void redisSetTest() {

        JSON users = JSONUtil.parse(USER_AN);
        JSON usersZhao = JSONUtil.parse(USER_ZHAO);

        redisCacheStr.setCache("age", "26");

        redisCacheStr.setCache("Name", "Zhao.An");

        redisCacheStr.setCache("Users", users.toString());

        HashSet<JSON> usersSet = new HashSet<>();
        usersSet.add(users);
        usersSet.add(usersZhao);
        redisCacheStr.setCache("usersSet", usersSet.toString());

        HashSet<String> strSet = new HashSet<>();
        strSet.add("Set_A");
        strSet.add("Set_B");
        redisCacheStr.setCache("strSet", strSet);

        HashSet<Integer> intSet = new HashSet<>();
        intSet.add(101);
        intSet.add(102);
        redisCacheStr.setCache("intSet", intSet.toString());

        List<JSON> usersList = new ArrayList<>();
        usersList.add(users);
        usersList.add(usersZhao);
        redisCacheStr.setCache("usersList", usersList.toString());

        List<String> strList = new ArrayList<>();
        strList.add("List_A");
        strList.add("List_B");
        redisCacheStr.setCache("strList", strList);

        List<Integer> intList = new ArrayList<>();
        intList.add(909);
        intList.add(908);
        redisCacheStr.setCache("intList", intList.toString());

        redisCacheStr.setCache("ageExpire", "26", 2L);

        redisCacheStr.setCache("NameExpire", "Zhao.An", 5L);

        redisCacheStr.setCache("UsersExpire", users.toString(), 3L);

        HashSet<JSON> usersSetExpire = new HashSet<>();
        usersSetExpire.add(users);
        usersSetExpire.add(usersZhao);
        redisCacheStr.setCache("usersSetExpire", usersSetExpire.toString(), 3L);

        List<JSON> usersListExpire = new ArrayList<>();
        usersListExpire.add(users);
        usersListExpire.add(usersZhao);
        redisCacheStr.setCache("usersListExpire", usersListExpire.toString(), 8L);


        // ZSet
        redisCacheStr.setCache("ZSetName", "Zhao.An", 4.253D);
        redisCacheStr.setCache("ZSetName", "Zhao.Jia", 3.52D);
        redisCacheStr.setCache("ZSetName", "Zhao", 90.243D);

        redisCacheStr.setCache("ZSetNameExpire", "users", 56.243D, 30L);

        Set<String> zSetName = redisCacheStr.rangeZSet("ZSetName", 2, 3);
        log.info(zSetName.toString());
        Set<String> zSetName1 = redisCacheStr.rangeZSet("ZSetName");
        log.info(zSetName1.toString());

        Set<String> reverseRangeZSet = redisCacheStr.reverseRangeZSet("ZSetName", 2, 3);
        log.info(reverseRangeZSet.toString());
        Set<String> reverseRangeZSet1 = redisCacheStr.reverseRangeZSet("ZSetName");
        log.info(reverseRangeZSet1.toString());

        Long zSetName2 = redisCacheStr.getZSetSize("ZSetName");
        log.info(zSetName2.toString());

        log.info(redisCacheStr.getZSetCard("ZSetName").toString());
        log.info(redisCacheStr.getZSetScore("ZSetName", "Zhao").toString());
        log.info(redisCacheStr.getCountZSet("ZSetName", 3.23, 91.12).toString());

        Double zSetName3 = redisCacheStr.incrementScoreZSet("ZSetName", "Zhao.Jia", 5.12);
        log.info("增加后：" + zSetName3);

        log.info(redisCacheStr.rankZSet("ZSetName", "Zhao").toString());
        log.info(redisCacheStr.reverseRankZSet("ZSetName", "Zhao.Jia").toString());
    }

    @Test
    public void toStringPretty() {

        System.out.println(JSONUtil.parse(USER_AN).toStringPretty());
        System.out.println(JSONUtil.parse(USER_ZHAO).toStringPretty());
    }

    private static final String USER_AN = "{\n" +
            "    \"id\": 1,\n" +
            "    \"nick_name\": \"Zhao.An\",\n" +
            "    \"nick_code\": \"W100001\",\n" +
            "    \"password_hash\": \"12345678\",\n" +
            "    \"salt\": null,\n" +
            "    \"telephone\": \"17xxxxxx23\",\n" +
            "    \"email\": \"Zhao.An@126.com\",\n" +
            "    \"profile_photo_url\": null,\n" +
            "    \"certification\": 1,\n" +
            "    \"real_name\": \"Zhao.An\",\n" +
            "    \"id_card_no\": \"6103xxxxxxx\",\n" +
            "    \"user_role\": null,\n" +
            "    \"place_org\": null,\n" +
            "    \"place_dept\": null,\n" +
            "    \"duty_block\": null,\n" +
            "    \"state\": \"ACTIVE\",\n" +
            "    \"deleted\": 0,\n" +
            "    \"last_login_time\": \"2024/08/12 14:28:36\",\n" +
            "    \"lock_version\": null,\n" +
            "    \"gmt_create\": \"2024/8/12 14:28:39\",\n" +
            "    \"gmt_modified\": \"2024/08/12 14:28:42\",\n" +
            "    \"temp\": null\n" +
            "  }";

    private static final String USER_ZHAO = "{\n" +
            "    \"id\": 2,\n" +
            "    \"nick_name\": \"Zhao.An\",\n" +
            "    \"nick_code\": \"W100002\",\n" +
            "    \"password_hash\": \"12345678\",\n" +
            "    \"salt\": null,\n" +
            "    \"telephone\": \"176932409823\",\n" +
            "    \"email\": \"Zhao.An@126.com\",\n" +
            "    \"profile_photo_url\": null,\n" +
            "    \"certification\": 1,\n" +
            "    \"real_name\": \"Zhao.An\",\n" +
            "    \"id_card_no\": \"610324xxxxx\",\n" +
            "    \"user_role\": null,\n" +
            "    \"place_org\": null,\n" +
            "    \"place_dept\": null,\n" +
            "    \"duty_block\": null,\n" +
            "    \"state\": \"ACTIVE\",\n" +
            "    \"deleted\": 0,\n" +
            "    \"last_login_time\": \"2024/08/12 14:28:36\",\n" +
            "    \"lock_version\": null,\n" +
            "    \"gmt_create\": \"2024/08/12 14:28:39\",\n" +
            "    \"gmt_modified\": \"2024/08/12 14:28:42\",\n" +
            "    \"temp\": null\n" +
            "  }";
}
