package com.ytempest.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * @author ytempest
 *         Description：IOC注解的辅助类，用于findViewById
 */
public class ViewFinder {
    private Activity mActivity;
    private View mView;

    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewFinder(View view) {
        this.mView = view;
    }

    public View findViewById(int viewId) {
        return mActivity != null ? mActivity.findViewById(viewId) : mView.findViewById(viewId);
    }
}
