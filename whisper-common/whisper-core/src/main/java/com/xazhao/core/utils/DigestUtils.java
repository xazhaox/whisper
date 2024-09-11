package com.xazhao.core.utils;

import com.xazhao.core.constant.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * @Description Created on 2024/08/29.
 * @Author Zhao.An
 */

@Slf4j
public class DigestUtils {

    /**
     * 字符编码
     */
    private static final String CHARSET = Charsets.UTF_8;

    /**
     * 加密器类型：加密算法为AES，加密模式为CBC，补码方式为PKCS5Padding
     */
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 算法
     */
    private static final String ALGORITHM = "AES";

    /**
     * 密钥
     */
    public static final String KEY = "PBKDF2WithHmacSHA256";

    /**
     * 生成密钥的密码
     */
    private static final String PASSWORD = "bdv9w8iu209w8efhd8923fq3fvdf";

    /**
     * 生成密钥的盐
     */
    private static final String SALT = "sdbvu203qre7f8dvob2gnethuioi";

    /**
     * 迭代计数
     */
    private static final Integer ITERATION_COUNT = 65536;

    /**
     * 密钥长度
     */
    private static final Integer KEY_LENGTH = 256;

    /**
     * 从密码和盐生成AES密钥
     */
    private static final SecretKey SECRET_KEY;

    static {
        try {
            SECRET_KEY = generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从密码和盐生成AES密钥
     *
     * @return SecretKey
     * @throws Exception Exception
     */
    private static SecretKey generateKey() throws Exception {
        // 使用PBKDF2密钥衍生函数生成密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY);
        KeySpec keySpec = new PBEKeySpec(PASSWORD.toCharArray(), SALT.getBytes(), ITERATION_COUNT, KEY_LENGTH);
        SecretKey temp = keyFactory.generateSecret(keySpec);
        return new SecretKeySpec(temp.getEncoded(), ALGORITHM);
    }

    /**
     * 加密
     *
     * @param content 待加密内容
     * @return 加密后
     */
    public static String encrypt(String content) {
        if (!StringUtils.isNotBlank(content)) {
            return null;
        }
        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            // 使用加密器的加密模式
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            // 加密
            byte[] encryptedData = cipher.doFinal(content.getBytes(CHARSET));
            // 使用BASE64对加密后的二进制数组进行编码
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @return 解密后
     */
    public static String decrypt(String content) {
        if (!StringUtils.isNotBlank(content)) {
            return null;
        }
        try {
            byte[] decodedData = Base64.getDecoder().decode(content);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            // 解密时使用加密器的解密模式
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
            byte[] decryptedData = cipher.doFinal(decodedData);
            return new String(decryptedData, CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
