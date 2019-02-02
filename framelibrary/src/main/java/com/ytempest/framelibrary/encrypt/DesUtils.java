package com.ytempest.framelibrary.encrypt;


import com.ytempest.framelibrary.encrypt.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ytempest
 *         Description：
 */
class DesUtils {

    private static final String KEY_ALGORITHM = "DES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    private static final String DES_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK0NxuvIsSXnhae9gBGEeh5rO29ch_cygM_8eNM-uDoSje0g_NzaZs4FG59kRRQ7P7Il9AHZsIO4lXptvwLWgmcCAwEAAQ";


    /**
     * DES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */

    public static String encrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 使用密钥初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(DES_KEY));
            // 加密
            byte[] result = cipher.doFinal(content.getBytes("utf-8"));
            //通过Base64转码返回
            return Base64.encodeBase64URLSafeString(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * DES 解密操作
     */
    public static String decrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(DES_KEY));
            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return 返回生成指定算法密钥生成器的 KeyGenerator 对象
     */
    private static SecretKeySpec getSecretKey(final String key) {
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            //DES 要求密钥长度为 56
            kg.init(56, new SecureRandom(key.getBytes()));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为DES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

