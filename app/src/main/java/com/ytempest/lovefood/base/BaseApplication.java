package com.ytempest.lovefood.base;

import android.app.Application;

import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.framelibrary.imageloader.GlideImageLoader;
import com.ytempest.framelibrary.skin.SkinManager;
import com.ytempest.framelibrary.util.LogUtils;
import com.ytempest.lovefood.util.ResourcesUtils;

/**
 * @author ytempest
 *         Description：
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化换肤框架
        SkinManager.getInstance().init(this);

        ImageLoaderManager.getInstance().init(new GlideImageLoader());

        initUtils();
    }

    private void initUtils() {
        ResourcesUtils.init(this);

        // 打印日志
        LogUtils.setEnable(true);
    }
}
