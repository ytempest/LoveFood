package com.ytempest.lovefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.ytempest.baselibrary.view.recyclerview.RefreshViewCreator;
import com.ytempest.lovefood.R;


/**
 * @author ytempest
 *         Description: 默认样式的刷新头部辅助类
 */
public class DefaultRefreshViewCreator extends RefreshViewCreator {
    /**
     * 加载数据的ImageView
     */
    private View mRefreshIv;
    private RotateAnimation mAnimation;

    public DefaultRefreshViewCreator() {
        mAnimation = new RotateAnimation(0, 720,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setRepeatCount(-1);
        mAnimation.setDuration(1000);
    }

    @Override
    public View getRefreshView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.view_refresh_header, parent, false);
        mRefreshIv = refreshView.findViewById(R.id.iv_refresh);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        float rotate = ((float) currentDragHeight) / refreshViewHeight;
        // 不断下拉的过程中旋转图片
        mRefreshIv.setRotation(rotate * 360);
    }

    @Override
    public void onRefreshing() {
        // 刷新的时候不断旋转
        mRefreshIv.startAnimation(mAnimation);
    }

    @Override
    public void onStopRefresh() {
        // 停止加载的时候清除动画
        mRefreshIv.setRotation(0);
        mRefreshIv.clearAnimation();
    }
}
