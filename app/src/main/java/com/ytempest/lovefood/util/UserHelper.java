package com.ytempest.lovefood.util;

import android.content.Context;

import com.ytempest.lovefood.http.data.UserInfo;

/**
 * @author ytempest
 *         Description：用户登录的辅助类，以及提供用户信息的存储和获取
 */
public class UserHelper {

    /**
     * 用户登录的信息文件名
     */
    private static String LOGIN_INFO = "login_info";
    private static String USER_DATA = "user_data";
    private static String LOGIN_STATUS = "login_status";

    private volatile static UserHelper INSTANCE;
    private Context mContext;

    private UserHelper() {
    }

    public static UserHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (UserHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserHelper();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public boolean isLogined() {
        return SpUtils.getInstance(mContext).getBoolean(LOGIN_INFO, LOGIN_STATUS, false);
    }

    public UserInfo getUserInfo() {
        String json = SpUtils.getInstance(mContext).getString(LOGIN_INFO, USER_DATA, "");
        return JSON.from(json, UserInfo.class);
    }

    /**
     * 存储用户的信息
     */
    public void saveUserInfo(UserInfo info) {
        SpUtils instance = SpUtils.getInstance(mContext);
        // 设置用户已经登录
        instance.putBoolean(LOGIN_INFO, LOGIN_STATUS, true);
        // 保存用户信息
        instance.putString(LOGIN_INFO, USER_DATA, JSON.to(info));
    }

    /**
     * 清除用户登录信息
     */
    public void clearUserInfo() {
        SpUtils instance = SpUtils.getInstance(mContext);
        // 注销用户
        instance.putBoolean(LOGIN_INFO, LOGIN_STATUS, false);
        // 清除用户信息
        instance.putString(LOGIN_INFO, USER_DATA, "");
    }

}
