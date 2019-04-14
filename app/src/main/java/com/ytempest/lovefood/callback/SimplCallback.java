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

    public static <FirstType, SecondType> void call(WrapCallback<FirstType, SecondType> callback,
                                                    FirstType firstParam, SecondType secondParam) {
        if (callback != null) {
            callback.onCall(firstParam, secondParam);
        }
    }
}
