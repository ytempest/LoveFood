package com.ytempest.lovefood.base;

import android.app.Application;

import com.mob.MobSDK;
import com.ytempest.baselibrary.base.BaseLibrary;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.framelibrary.base.FrameLibrary;
import com.ytempest.lovefood.util.DrawUtils;
import com.ytempest.lovefood.util.UserHelper;


/**
 * @author ytempest
 *         Description：
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        BaseLibrary.init(this);

        FrameLibrary.init(this);

        // 初始化 MobSDK
        MobSDK.init(this, "27cdc1d5f5ff5", "558df308bbb5de301500d31121438c15");

        initUtils();
    }

    private void initUtils() {
        // 打印日志
        LogUtils.setEnable(true);

        // 初始化用户数据辅助类
        UserHelper.getInstance().init(this);

        DrawUtils.init(this);
    }
}
