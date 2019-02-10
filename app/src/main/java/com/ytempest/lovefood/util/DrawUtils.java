package com.ytempest.lovefood.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author ytempest
 *         Description：
 */
public class DrawUtils {

    private static Context context;

    public static void init(Context context) {
        context = context.getApplicationContext();
    }

    public static int dpToPx(float dp) {
        return toPx(TypedValue.COMPLEX_UNIT_DIP, dp);
    }

    public static int spToPx(float sp) {
        return toPx(TypedValue.COMPLEX_UNIT_SP, sp);
    }

    public static int toPx(int type, float value) {
        return (int) TypedValue.applyDimension(
                type, value, context.getResources().getDisplayMetrics());
    }
}
