package com.xazhao.security.config;

import com.xazhao.security.handler.BussinessAccessDeniedHandler;
import com.xazhao.security.handler.LoginFailureHandler;
import com.xazhao.security.handler.LoginSuccessHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringSecurity 配置类，配置安全链
 *
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityAutoConfiguration {

    /**
     * 设置哪些路径可以直接访问，不需要认证
     */
    public static final String[] REQUEST_MATCHERS = new String[]{
            "/auth/login/**"
    };

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private BussinessAccessDeniedHandler bussinessAccessDeniedHandler;

    /**
     * SpringSecurity 配置类，配置安全链
     *
     * @param http HttpSecurity 对象，用于构建安全策略
     * @return SecurityFilterChain 对象，表示配置好的安全链
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 关闭跨站点请求伪造csrf防护
        http.csrf(AbstractHttpConfigurer::disable);

        // 前后端分离认证逻辑
        http.formLogin((formLogin) -> formLogin
                .loginProcessingUrl("/auth/login/account")
                // 登录成功处理逻辑
                .successHandler(loginSuccessHandler)
                // 登录失败处理逻辑
                .failureHandler(loginFailureHandler)
        );

        // 对请求进行访问控制设置
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                // 设置哪些路径可以直接访问，不需要认证
                .requestMatchers(REQUEST_MATCHERS).permitAll()
                // 除以上路径外，其他路径的请求都需要认证
                .anyRequest().authenticated()
        );

        // 访问受限后的异常处理
        http.exceptionHandling((exceptionHandling) ->
                exceptionHandling.accessDeniedHandler(bussinessAccessDeniedHandler)
        );

        return http.build();
    }
}
