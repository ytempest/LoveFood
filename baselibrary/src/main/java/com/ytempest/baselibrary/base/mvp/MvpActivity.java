package com.ytempest.baselibrary.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;

/**
 * @author ytempest
 *         Description：
 */
public abstract class MvpActivity<Presenter extends IPresenter> extends AppCompatActivity implements IView, IContract {

    private Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return this;
    }

    @Override
    public Context getAppContext() {
        return this.getApplicationContext();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }
}