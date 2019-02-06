package com.ytempest.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.MvpFragment;
import com.ytempest.baselibrary.view.CustomToast;

import butterknife.ButterKnife;


/**
 * @author ytempest
 */
public abstract class BaseFragment<Presenter extends IPresenter> extends MvpFragment<Presenter> {

    protected View rootView;
    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContext = getActivity();
        rootView = View.inflate(mContext, getLayoutId(), null);

        // 加入注解
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 启动Activity
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    /**
     * 启动Activity，带结果
     */
    protected void startActivityForResult(Class<?> clazz, int requetCode) {
        Intent intent = new Intent(mContext, clazz);
        startActivityForResult(intent, requetCode);
    }

    protected void showToast(String tip) {
        CustomToast.getInstance().show(tip);
    }


    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

}
