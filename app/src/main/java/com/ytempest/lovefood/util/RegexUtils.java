package com.ytempest.lovefood.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ytempest
 *         Description：
 */
public class RegexUtils {
    private static Pattern ACCOUNT_REGEX = Pattern.compile("[a-zA-Z0-9]{3,12}");
    private static Pattern PASSWORD_REGEX = Pattern.compile("[a-zA-Z0-9]{6,18}");
    public static final Pattern PHONE_REGEX = Pattern.compile("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
    public static final Pattern EMAIL_REGEX = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");

    public static boolean isAccount(String account) {
        return isMatch(ACCOUNT_REGEX, account);
    }

    public static boolean isPassword(String pwd) {
        return isMatch(PASSWORD_REGEX, pwd);
    }

    public static boolean isPhone(String phone) {
        return isMatch(PHONE_REGEX, phone);
    }

    public static boolean isEmail(String email) {
        return isMatch(EMAIL_REGEX, email);
    }

    public static boolean isMatch(Pattern regex, String str) {
        Matcher m = regex.matcher(str);
        return m.matches();
    }
}
