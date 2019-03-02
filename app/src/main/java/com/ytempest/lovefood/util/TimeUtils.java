package com.ytempest.lovefood.util;

import java.util.Calendar;

public class TimeUtils {
    public static final long SECOND_IN_MILLIS = 1000;
    public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
    public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
    public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
    public static final long WEEK_IN_MILLIS = DAY_IN_MILLIS * 7;
    public static final long MONTH_IN_MILLIS = DAY_IN_MILLIS * 30;
    public static final long YEAR_IN_MILLIS = DAY_IN_MILLIS * 365;

    public static boolean isToday(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long todayStart = calendar.getTimeInMillis();
        return timestamp > todayStart && timestamp < todayStart + DAY_IN_MILLIS;
    }

    public static long getDayStart(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long getDayStart(Calendar calendar) {
        long origin = calendar.getTimeInMillis();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long result = calendar.getTimeInMillis();
        calendar.setTimeInMillis(origin);
        return result;
    }

    public static long getTodayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static boolean isYesterday(long timestamp) {
        long todayStart = getTodayStart();
        if (todayStart - timestamp < DAY_IN_MILLIS) {
            return true;
        }
        return false;
    }

    public static boolean isThisYear(long timestamp) {
        long todayStart = getTodayStart();
        if (todayStart - timestamp < YEAR_IN_MILLIS) {
            return true;
        }
        return false;
    }
}
