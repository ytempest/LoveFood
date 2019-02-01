package com.ytempest.baselibrary.base.mvp;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class Utils {
    public static <T> T get(Class<T> clazz) {
        if (clazz.isInterface()) {
            throw new IllegalStateException(String.format("@%s注入的数据不能为接口", clazz.getSimpleName()));
        }

        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException(String.format("请为%s提供一个无参构造方法", clazz.getCanonicalName()));
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(String.format("%s的构造方法的修饰符必须为public", clazz.getCanonicalName()));
        }
    }
}
