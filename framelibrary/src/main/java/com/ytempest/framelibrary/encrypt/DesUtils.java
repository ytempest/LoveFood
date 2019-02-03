package com.ytempest.framelibrary.encrypt;


import android.text.TextUtils;

import com.ytempest.framelibrary.encrypt.binary.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author ytempest
 *         Description：
 */
class DesUtils {

    private static final String DES_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAK0NxuvIsSXnhae9gBGEeh5rO29ch_cygM_8eNM-uDoSje0g_NzaZs4FG59kRRQ7P7Il9AHZsIO4lXptvwLWgmcCAwEAAQ";

    private static final String ALGORITHM_MODE = "DES";
    private static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    private static final String IV_PARAMETER_SPEC = "12345678";
    private static final String UTF_8 = "UTF-8";


    /**
     * DES加密算法
     */
    public static String encrypt(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            DESKeySpec dks = new DESKeySpec(DES_KEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_MODE);
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(data.getBytes(UTF_8));
            return Base64.encodeBase64URLSafeString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES 解密算法
     */
    public static String decrypt(String data) {
        if (TextUtils.isEmpty(data)) {
            return null;
        }
        try {
            DESKeySpec dks = new DESKeySpec(DES_KEY.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_MODE);
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER_SPEC.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            return new String(cipher.doFinal(Base64.decodeBase64(data)), UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

}

