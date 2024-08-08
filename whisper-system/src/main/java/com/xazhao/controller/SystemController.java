package com.xazhao.controller;

import com.xazhao.entity.InvokeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description Created on 2024/08/05.
 * @Author xaZhao
 */

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/system/user")
public class SystemController {

    @Value("${nacos.group}")
    private String nacosGroup;

    @GetMapping("/insert")
    public InvokeResult<Map<String, Object>> userInsert() {

        Map<String, Object> parameterMap = new HashMap<>(16);
        parameterMap.put("returnMsg", "插入用户成功！");
        parameterMap.put("nacos", nacosGroup);

        return InvokeResult.success(parameterMap);
    }
}
