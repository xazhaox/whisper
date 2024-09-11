package com.xazhao.gateway.filter.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * <h3>跨域 Filter</h3>
 *
 * @Description Created on 2024/08/07.
 * @Author Zhao.An
 */

@Configuration
public class CorsFilter {

    private static final String ALLOWED_HEADERS = "*";

    private static final String ALLOWED_ORIGIN = "*";

    private static final String ALLOWED_HEADER = "*";

    private static final Long MAX_AGE = 360000L;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 允许的请求方式
        corsConfig.addAllowedMethod(ALLOWED_HEADERS);
        // 允许的来源
        corsConfig.addAllowedOriginPattern(ALLOWED_ORIGIN);
        // 允许的请求头参数
        corsConfig.addAllowedHeader(ALLOWED_HEADER);
        // 在跨域请求的时候使用同一个session
        corsConfig.setAllowCredentials(true);
        // 这次跨域检测的有效期
        corsConfig.setMaxAge(MAX_AGE);

        // 运行访问的资源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
