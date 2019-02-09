package com.ytempest.lovefood.util;

import android.text.TextUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytempest
 *         Description：
 */
public class DateUtils {

    public static final String DATE_SEPARATOR = "-";
    private static final SimpleDateFormat FORMAT =
            new SimpleDateFormat(String.format("yyyy-MM-dd", DATE_SEPARATOR, DATE_SEPARATOR));

    public static String format(Long timestamp) {
        if (timestamp != null) {
            return format(new Date(timestamp));
        }
        return "";
    }

    public static String format(Date date) {
        if (date != null) {
            return FORMAT.format(date);
        }
        return "";
    }

    public static String format(int year, int month, int day) {
        return String.format("%04d%s%02d%s%02d",
                year, DATE_SEPARATOR, month, DATE_SEPARATOR, day);
    }

    public static Date stringToDate(String date) {
        if (!TextUtils.isEmpty(date)) {
            try {
                return FORMAT.parse(date);
            } catch (ParseException e) {
            }
        }
        return null;
    }

    public static long stringToLong(String date) {
        if (!TextUtils.isEmpty(date)) {
            return stringToDate(date).getTime();
        }
        return -1;
    }
}
