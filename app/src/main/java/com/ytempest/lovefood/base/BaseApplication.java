package com.ytempest.lovefood.base;

import android.app.Application;

import com.ytempest.baselibrary.base.BaseLibrary;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.framelibrary.base.FrameLibrary;


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
    }
}
