package com.xazhao.auth.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.xazhao.cache.constant.CacheConstant;
import com.xazhao.auth.constant.Constant;
import com.xazhao.cache.core.RedisCache;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import static com.xazhao.cache.constant.CacheConstant.LOGIN_HASH_TABLE_KEY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Jwt配置
 *
 * @Description Created on 2024/08/15.
 * @Author Zhao.An
 */

@Slf4j
@Component
@EnableConfigurationProperties(JwtSecurityProperties.class)
public class JwtSecurity implements InitializingBean {

    private final JwtSecurityProperties jwtSecurityProperties;

    /**
     * token默认有效时间30分钟，nacos中可以通过token-validity-in-seconds属性设置过期时间
     */
    private static final Long VALID_TIME = 30 * 60 * 1000L;

    /**
     * SecretKey实例
     */
    private Key secretKey;

    @Resource
    private RedisCache redisCache;

    /**
     * 构造器注入
     *
     * @param jwtSecurityProperties JwtSecurityProperties
     */
    public JwtSecurity(JwtSecurityProperties jwtSecurityProperties) {
        this.jwtSecurityProperties = jwtSecurityProperties;
    }

    /**
     * 所有属性设置完成后，初始化令牌
     *
     * @throws Exception Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 使用BASE64进行编码
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityProperties.getBase64Secret());
        // 基于指定的密钥字节数组创建新的 SecretKey 实例
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 用户登陆时生成token
     *
     * @param claims 声明数据
     * @return token token
     */
    public String generateToken(Map<String, Object> claims) {
        // 使用雪花算法ID作为该token标识，可改为用户编码或用户ID作为token标识
        claims.put(Constant.USER_KEY, IdUtil.getSnowflake().nextIdStr());
        String userId = String.valueOf(claims.get(CacheConstant.USER_ID));
        // 判断redis中是否有该key
        Boolean isExists = redisCache.hasKey(LOGIN_HASH_TABLE_KEY, userId);
        if (Boolean.TRUE.equals(isExists)) {
            // 存在，删除旧token
            redisCache.deleteHash(LOGIN_HASH_TABLE_KEY, userId);
        }
        // 不存在，生成token并保存redis
        String userToken = initializingToken(claims);
        Long securityTime = jwtSecurityProperties.getTokenValidityInSeconds();
        // token有效时间
        Long expireTime = null != securityTime ? securityTime : VALID_TIME;
        // 保存到redis的hash表中，hash表名为login_hash_table，表中的key为用户ID，对应一个token
        redisCache.setCache(LOGIN_HASH_TABLE_KEY, userId, userToken, expireTime);
        // 返回用户token
        return userToken;
    }

    /**
     * 生成token
     *
     * @param claims 声明数据
     * @return JWT字符串
     */
    private String initializingToken(Map<String, Object> claims) {
        Long securityTime = jwtSecurityProperties.getTokenValidityInSeconds();
        // token有效时间
        Long expireTime = null != securityTime ? securityTime : VALID_TIME;
        JwtBuilder jwt = Jwts.builder()
                .setId(IdUtil.getSnowflake().nextIdStr())
                .setSubject("JWT")
                // 发行时间
                .setIssuedAt(new Date())
                //  密钥，签名算法
                .signWith(secretKey, SignatureAlgorithm.HS512)
                // 对载荷进行压缩
                .compressWith(CompressionCodecs.DEFLATE)
                // token有效时间;
                .setExpiration(new Date(System.currentTimeMillis() + expireTime));
        if (CollUtil.isNotEmpty(claims)) {
            // 将Jwt有效负载设置为由指定名称值对填充的JSON声明实例
            jwt.claim(CacheConstant.AUTHORITIES_KEY, claims);
        }
        return jwt.compact();
    }

    /**
     * 获取Token声明数据
     *
     * @param token token
     * @return Token声明数据
     */
    public Claims getClaims(String token) {
        if (!isNotBlank(token)) {
            return null;
        }
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            e.printStackTrace();
            log.error("Invalid Jwt signature.");
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            log.error("Expired Jwt token.");
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            log.error("Unsupported Jwt token.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            log.error("Jwt token compact of handler are invalid.");
        }
        return null;
    }

    /**
     * 刷新Token
     *
     * @param token 原始Token
     * @return 新Token
     */
    public String refreshToken(String token) {
        Claims claims = getClaims(token);
        if (null == claims) {
            return null;
        }
        return initializingToken(claims);
    }

    /**
     * 验证Token是否过期<br>
     * {@link Boolean#FALSE} 未过期，{@link Boolean#TRUE} 过期
     *
     * @param token Token
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        Claims claims = getClaims(token);
        if (null == claims) {
            return null;
        }
        // 若token有效时间在当前时间之前则未过期
        return claims.getExpiration().before(new Date());
    }

    /**
     * 根据Token获取openId
     *
     * @param token Token
     * @return openId
     */
    public String getTokenOpen(String token) {
        Claims claims = getClaims(token);
        if (null == claims) {
            return null;
        }
        String claimsId = claims.getId();
        return isNotBlank(claimsId) ? claimsId : null;
    }
}
