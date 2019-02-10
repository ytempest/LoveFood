package com.ytempest.lovefood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.LoadViewCreator;
import com.ytempest.lovefood.R;


/**
 * @author ytempest
 *         Description：
 */
public class DefaultLoadViewCreator extends LoadViewCreator {
    private TextView mLoadTv;
    private View mRefreshIv;
    private RotateAnimation mAnimation;

    public DefaultLoadViewCreator() {
        mAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(-1);
        mAnimation.setDuration(1200);
    }

    @Override
    public View getLoadView(Context context, ViewGroup parent) {
        View refreshView = LayoutInflater.from(context).inflate(R.layout.view_load_footer, parent, false);
        mLoadTv = (TextView) refreshView.findViewById(R.id.tv_load);
        mRefreshIv = refreshView.findViewById(R.id.iv_load);
        return refreshView;
    }

    @Override
    public void onPull(int currentDragHeight, int refreshViewHeight, int currentRefreshStatus) {
        if (currentRefreshStatus == LoadRecyclerView.LOAD_STATUS_ON_PULL) {
            mLoadTv.setText("上拉加载更多");
        }
        if (currentRefreshStatus == LoadRecyclerView.LOAD_STATUS_LOOSE_PULL) {
            mLoadTv.setText("松开加载更多");
        }
    }

    @Override
    public void onLoading() {
        mLoadTv.setVisibility(View.INVISIBLE);
        mRefreshIv.setVisibility(View.VISIBLE);

        // 加载的时候不断旋转
        mRefreshIv.startAnimation(mAnimation);
    }

    @Override
    public void onStopLoad() {
        // 停止加载的时候清除动画
        mRefreshIv.clearAnimation();
        mLoadTv.setText("上拉加载更多");
        mLoadTv.setVisibility(View.VISIBLE);
        mRefreshIv.setVisibility(View.INVISIBLE);
    }
}
