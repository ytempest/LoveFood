package com.ytempest.lovefood.util;

import java.util.Collection;
import java.util.List;

/**
 * @author ytempest
 *         Description：
 */
public class DataUtils {
    public static int getSize(List<?> list) {
        return list != null ? list.size() : 0;
    }

    public static int getSize(Collection<?> collection) {
        return collection != null ? collection.size() : 0;
    }
}
