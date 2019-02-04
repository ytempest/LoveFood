package com.ytempest.lovefood.base;

import android.app.Application;

import com.ytempest.baselibrary.base.BaseLibrary;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.framelibrary.base.FrameLibrary;
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

        initUtils();
    }

    private void initUtils() {
        // 打印日志
        LogUtils.setEnable(true);

        // 初始化用户数据辅助类
        UserHelper.getInstance().init(this);
    }
}
