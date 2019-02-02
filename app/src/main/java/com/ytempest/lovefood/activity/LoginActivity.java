package com.ytempest.lovefood.activity;

import android.support.v7.widget.Toolbar;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.LoginContract;
import com.ytempest.lovefood.presenter.LoginPresenter;

/**
 * @author ytempest
 * @date 2019/2/2
 */
@InjectPresenter(LoginPresenter.class)
public class LoginActivity extends BaseSkinActivity<LoginContract.Presenter> implements LoginContract.LoginView, LoginContract {

    @Override
    protected int getLayoutResId() {
        // TODO  heqidu: 尝试将的app样式统一抽取到style中
        return R.layout.activity_login;
    }

    @Override
    protected void initTitle() {
        Toolbar tooBar = findViewById(R.id.toolbar);
        setSupportActionBar(tooBar);
        //设置不现实自带的title文字
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}