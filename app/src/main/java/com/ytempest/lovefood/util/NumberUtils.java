package com.ytempest.lovefood.util;

import android.text.TextUtils;

/**
 * @author ytempest
 *         Description：
 */
public class NumberUtils {

    public static Integer parseInteger(String val) {
        Integer num = null;
        if (!TextUtils.isEmpty(val)) {
            num = Integer.parseInt(val);
        }
        return num;
    }

    public static Long parseLong(String val) {
        Long num = null;
        if (!TextUtils.isEmpty(val)) {
            num = Long.parseLong(val);
        }
        return num;
    }

    public static int getInt(Integer val) {
        if (val != null) {
            return val;
        }
        return 0;
    }

    public static long getLong(Long val) {
        if (val != null) {
            return val;
        }
        return 0L;
    }
}
