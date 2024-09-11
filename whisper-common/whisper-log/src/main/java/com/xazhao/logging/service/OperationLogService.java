package com.xazhao.logging.service;

import com.xazhao.logging.mapper.OperationLogMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Slf4j
@Component
public class OperationLogService {

    @Resource
    private OperationLogMapper sysOperationLogMapper;


}
