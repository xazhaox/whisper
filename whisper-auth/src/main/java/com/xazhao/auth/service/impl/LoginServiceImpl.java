package com.xazhao.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.xazhao.auth.entity.LoginParam;
import com.xazhao.auth.entity.LoginUser;
import com.xazhao.auth.exception.AuthErrorCode;
import com.xazhao.auth.exception.AuthenticationException;
import com.xazhao.auth.security.JwtSecurity;
import com.xazhao.auth.service.LoginService;
import com.xazhao.core.concurrent.AsyncRunnableTask;
import com.xazhao.core.entity.InvokeResult;
import com.xazhao.core.utils.DigestUtils;
import com.xazhao.logging.constant.Login;
import com.xazhao.logging.factory.AsyncFactory;
import com.xazhao.system.entity.Users;
import com.xazhao.system.mapper.UsersMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.ClassUtils;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * @Description Created on 2024/08/20.
 * @Author Zhao.An
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * JDBC 事务管理器
     */
    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
     * 定义事务属性
     */
    @Resource
    private TransactionDefinition transactionDefinition;

    /**
     * 表示用户被冻结
     */
    public static final String FROZEN = "FROZEN";

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private JwtSecurity jwtSecurity;

    @Resource
    private AsyncRunnableTask asyncRunnableTask;

    @Resource
    private ThreadPoolTaskExecutor executorService;

    /**
     * 用户登录，账号登录接口
     *
     * @param loginParam 登录信息
     * @return 登录信息（Token）
     */
    @Override
    public InvokeResult<LoginUser> accountLogin(LoginParam loginParam) {
        String userCode = loginParam.getUserCode();
        Users user = usersMapper.getUserOne(userCode);
        if (null == user) {
            checkLoginLog(userCode, userCode, Login.LOGIN_FAIL, Login.FAIL_MSG);
            throw new AuthenticationException(AuthErrorCode.USER_NOT_EXIST);
        }
        if (StringUtils.equals(FROZEN, user.getState())) {
            checkLoginLog(userCode, userCode, Login.LOGIN_FAIL, Login.FROZEN_MSG);
            throw new AuthenticationException(AuthErrorCode.USER_STATUS_IS_NOT_ACTIVE);
        }
        // 比对密码
        String encrypt = DigestUtils.encrypt("23623456345634");
        // String decryptPwd = AesUtils.decrypt(loginParam.getPassword());
        // if (isNotBlank(decryptPwd) && !user.getPasswordHash().equals(SM3.hashHex(decryptPwd))) {
        //     checkLoginLog(userCode, userCode, Login.LOGIN_FAIL, Login.FAIL_MSG);
        //     throw new LoginException(Login.FAIL_MSG);
        // }
        // 账号密码正确，设置菜单权限

        // 生成token
        Map<String, Object> userMap = BeanUtil.beanToMap(user);
        String token = jwtSecurity.generateToken(userMap);
        LoginUser loginUser = LoginUser.builder()
                .token(token)
                .build();
        checkLoginLog(userCode, userCode, Login.LOGIN_SUCCESS, Login.SUCCESS_MSG);
        return InvokeResult.success(loginUser);
    }

    /**
     * 用户登录，微信登录接口
     *
     * @param loginParam 登录信息
     * @return 登录信息（Token）
     */
    @Override
    public InvokeResult<LoginUser> weChatLogin(LoginParam loginParam) {

        ClassUtils.isPresent("", null);

        // 设置事务属性

        // 手动获取事务
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        try {

            // 操作数据库
            log.info("模拟操作数据库操作：insert，delete，update");

            // 提交事务
            transactionManager.commit(transactionStatus);

        } catch (TransactionException e) {

            // 编程式事务，手动回滚事务
            transactionManager.rollback(transactionStatus);

            throw new AuthenticationException(AuthErrorCode.LOGIN_FAILED);
        }

        return null;
    }

    /**
     * 异步记录登录日志
     *
     * @param userCode 用户代码
     * @param userName 用户名称
     * @param status   登录状态
     * @param message  登录信息
     */
    public void checkLoginLog(String userCode, String userName, String status, String message) {
        TimerTask timerTask = AsyncFactory.checkLoginLog(userCode, userName, status, message);
        asyncRunnableTask.invoke(timerTask);
    }

    /**
     * 测试Mybatis Map返回的Key是否转为小写
     *
     * @return Map
     */
    @Override
    public List<Map<String, Object>> pageMapQuery() {

        return usersMapper.pageMapQuery();
    }
}
