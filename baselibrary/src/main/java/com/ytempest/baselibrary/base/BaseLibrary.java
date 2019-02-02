package com.ytempest.baselibrary.base;

import android.content.Context;

import com.ytempest.baselibrary.util.ResourcesUtils;
import com.ytempest.baselibrary.view.CustomToast;

/**
 * @author ytempest
 * @date 2019/2/2
 */
public final class BaseLibrary {


    private BaseLibrary() {
    }

    public static void init(Context context) {
        ResourcesUtils.init(context);
        CustomToast.getInstance().init(context);
    }

}
