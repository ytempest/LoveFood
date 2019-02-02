package com.ytempest.framelibrary.encrypt;


/**
 * @author ytempest
 * @date 2019/2/1
 */
public class EncryptUtils {

    /**
     * 加密
     */
    public static String encrypt(String source) {
        try {
            // TODO  heqidu: 加密后的字符串会不会出现等于号
            String rsaEncrypt = RsaUtils.encryptByPublicKey(source);
            return DesUtils.encrypt(rsaEncrypt);
        } catch (Exception e) {
            throw new IllegalStateException("无法对数据进行加密");
        }
    }
}