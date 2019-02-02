package com.ytempest.framelibrary.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author ytempest
 * @date 2019/2/2
 */
public class LogUtils {
    public static final String TAG = "【LoveFood】";
    private static boolean IS_LOG = true;

    public static void setEnable(boolean enable) {
        IS_LOG = true;
    }

    private static boolean isShowLog() {
        return IS_LOG;
    }

    public static void d(String tag, Object... msgs) {
        if (isShowLog()) {
            String result = build(tag, msgs);
            Log.d(TAG, result);
        }
    }

    public static void i(String tag, Object... msgs) {
        if (isShowLog()) {
            String result = build(tag, msgs);
            Log.i(TAG, result);
        }
    }


    public static void e(String tag, Object... msgs) {
        if (isShowLog()) {
            String result = build(tag, msgs);
            Log.e(TAG, result);
        }
    }

    private static String build(String tag, Object... msgs) {
        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(tag)) {
            builder.append("[").append(tag).append("]: ");
        }
        for (Object msg : msgs) {
            builder.append(msg);
        }
        return builder.toString();
    }
}
