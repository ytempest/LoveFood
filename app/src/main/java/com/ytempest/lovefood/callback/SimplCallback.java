package com.ytempest.lovefood.callback;

/**
 * @author ytempest
 *         Description：
 */
public class SimplCallback {
    public static <Type> void call(Callback<Type> callback) {
        call(callback, null);
    }

    public static <Type> void call(Callback<Type> callback, Type param) {
        if (callback != null) {
            callback.onCall(param);
        }
    }
}
