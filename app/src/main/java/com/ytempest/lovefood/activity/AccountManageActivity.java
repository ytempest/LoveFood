package com.ytempest.lovefood.activity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.AccountManageContract;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.presenter.AccountManagePresenter;
import com.ytempest.lovefood.presenter.UpdateUserPresenter;

@InjectPresenter(AccountManagePresenter.class)
public class AccountManageActivity extends BaseSkinActivity<AccountManageContract.Presenter>
        implements AccountManageContract.AccountManagerView, AccountManageContract {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_account_manage;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
