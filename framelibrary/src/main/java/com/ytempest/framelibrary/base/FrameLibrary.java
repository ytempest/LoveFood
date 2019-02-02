package com.ytempest.framelibrary.base;

import android.content.Context;

import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.framelibrary.imageloader.GlideImageLoader;
import com.ytempest.framelibrary.skin.SkinManager;

/**
 * @author ytempest
 * @date 2019/2/2
 */
public class FrameLibrary {
    private FrameLibrary() {
    }

    public static void init(Context context) {
        // 初始化换肤框架
        SkinManager.getInstance().init(context);

        ImageLoaderManager.getInstance().init(new GlideImageLoader());
    }

}
