package com.ytempest.lovefood.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytempest
 *         Description：
 */
public class DateFormatUtils {

    public static final String DATE_SEPARATOR = "-";
    private static final SimpleDateFormat DATE =
            new SimpleDateFormat(String.format("yyyy%sMM%sdd", DATE_SEPARATOR, DATE_SEPARATOR));
    private static final SimpleDateFormat DATE_TIME_WITHOUT_YEAR =
            new SimpleDateFormat(String.format("MM%sdd HH:mm", DATE_SEPARATOR));
    private static final SimpleDateFormat DATE_TIME =
            new SimpleDateFormat(String.format("yyyy%sMM%sdd HH:mm", DATE_SEPARATOR, DATE_SEPARATOR));
    private static final SimpleDateFormat TIME =
            new SimpleDateFormat("HH:mm");

    public static String format(SimpleDateFormat format, Long timestamp) {
        if (timestamp != null) {
            return format(format, new Date(timestamp));
        }
        return "";
    }

    public static String format(SimpleDateFormat format, Date date) {
        if (format != null && date != null) {
            return format.format(date);
        }
        return "";
    }

    public static String formatDate(Long timestamp) {
        if (timestamp != null) {
            return format(DATE, new Date(timestamp));
        }
        return "";
    }

    public static String formatDate(Date date) {
        if (date != null) {
            return format(DATE, date);
        }
        return "";
    }

    public static String formatDate(int year, int month, int day) {
        return String.format("%04d%s%02d%s%02d",
                year, DATE_SEPARATOR, month, DATE_SEPARATOR, day);
    }

    public static String formatTime(Long timestamp) {
        String time = null;
        if (TimeUtils.isToday(timestamp)) {
            time = format(TIME, timestamp);

        } else if (TimeUtils.isYesterday(timestamp)) {
            time = "昨天 " + format(TIME, timestamp);

        } else if (TimeUtils.isThisYear(timestamp)) {
            time = format(DATE_TIME_WITHOUT_YEAR, timestamp);

        } else {
            time = format(DATE_TIME, timestamp);
        }
        return time;
    }

    public static Date stringToDate(String date) {
        if (!TextUtils.isEmpty(date)) {
            try {
                return DATE.parse(date);
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
