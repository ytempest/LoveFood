package com.ytempest.baselibrary.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;


/**
 * @author ytempest
 */
public abstract class MvpFragment<Presenter extends IPresenter> extends Fragment implements IView, IContract {

    private Presenter mPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createPresenter();
    }

    private void createPresenter() {
        InjectPresenter injectPresenter = this.getClass().getAnnotation(InjectPresenter.class);
        if (injectPresenter == null) {
            throw new IllegalStateException(String.format("请使用@%s注解为%s注入Presenter", InjectPresenter.class.getSimpleName(), this.getClass().getCanonicalName()));
        }
        Class<? extends IPresenter> clazz = injectPresenter.value();

        mPresenter = (Presenter) Utils.get(clazz);

        // 添加协议
        mPresenter.setContract(this);

        // attach这个View
        mPresenter.attach(this);
    }

    public Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}
