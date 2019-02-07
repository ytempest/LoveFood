package com.ytempest.lovefood.activity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.presenter.UpdateUserPresenter;

import butterknife.BindView;

@InjectPresenter(UpdateUserPresenter.class)
public class UpdateUserActivity extends BaseSkinActivity<UpdateUserContract.Presenter>
        implements UpdateUserContract.UpdateUserView, UpdateUserContract {

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @Override

    protected int getLayoutResId() {
        return R.layout.activity_update_userinfo;
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

}
