package com.ytempest.lovefood.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ytempest
 *         Description：
 */
public class DateUtils {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd");

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
}
