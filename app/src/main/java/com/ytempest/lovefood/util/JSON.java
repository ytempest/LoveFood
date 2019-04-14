package com.ytempest.lovefood.util;

import com.google.gson.Gson;

/**
 * @author ytempest
 *         Description：
 */
public class JSON {
    private static final Gson GSON = new Gson();

    public static String to(Object src) {
        return GSON.toJson(src);
    }

    public static <T> T from(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }
}
