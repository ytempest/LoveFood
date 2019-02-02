package com.ytempest.framelibrary.encrypt;


import com.ytempest.framelibrary.encrypt.binary.Base64;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class EncryptUtils {

    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJbATosw41+FrA2CthmQbBSyswYrOgiNwtM7jlP0cJ/qof2tLK5sOrh43WBZ8aD8/Iec0jsT0gWi2eZbc9KL1NsCAwEAAQ==";

    private static final String RSA = "RSA";
    private static final String ALGORITHM_RSA = "RSA/ECB/PKCS1Padding";


    /**
     * 获取公钥
     */
    private static PublicKey getPublicKey(String publicKey) throws Exception {
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return keyFactory.generatePublic(publicKeySpec);
    }


    /**
     * 公钥加密
     */
    public static String encrypt(String source) throws Exception {
        PublicKey publicKey = getPublicKey(PUBLIC_KEY);
        Cipher cipher = Cipher.getInstance(ALGORITHM_RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        cipher.update(source.getBytes("UTF-8"));
        return encodeBase64(cipher.doFinal());
    }


    /**
     * Base64编码
     */
    public static String encodeBase64(byte[] source) throws Exception {
        return Base64.encodeBase64URLSafeString(source);
    }

    /**
     * Base64解码
     */
    public static byte[] decodeBase64(String target) throws Exception {
        return Base64.decodeBase64(target);
    }

}