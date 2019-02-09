package com.ytempest.lovefood.activity;

import android.content.Intent;
import android.view.View;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.AccountManageContract;
import com.ytempest.lovefood.presenter.AccountManagePresenter;

import butterknife.BindView;
import butterknife.OnClick;

@InjectPresenter(AccountManagePresenter.class)
public class AccountManageActivity extends BaseSkinActivity<AccountManageContract.Presenter>
        implements AccountManageContract.AccountManagerView, AccountManageContract {

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_account_manage;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initData() {

    }

    /* Click */

    @OnClick(R.id.tv_update_user_info)
    protected void onUpdateUserInfoClick(View view) {
        Intent intent = new Intent(AccountManageActivity.this, UpdateUserActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.tv_update_pwd)
    protected void onUpdatePwdClick(View view) {
        Intent intent = new Intent(AccountManageActivity.this, UpdatePasswordActivity.class);
        startActivity(intent);
    }

}
