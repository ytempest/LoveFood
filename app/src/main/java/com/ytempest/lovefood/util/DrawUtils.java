package com.ytempest.lovefood.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author ytempest
 *         Description：
 */
public class DrawUtils {

    public static int dpToPx(Context context, float dp) {
        return toPx(context, TypedValue.COMPLEX_UNIT_DIP, dp);
    }

    public static int spToPx(Context context, float sp) {
        return toPx(context, TypedValue.COMPLEX_UNIT_SP, sp);
    }

    public static int toPx(Context context, int type, float value) {
        return (int) TypedValue.applyDimension(
                type, value, context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
}
