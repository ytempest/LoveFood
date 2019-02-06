package com.ytempest.lovefood.activity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.MainContract;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.presenter.PreviewUserPresenter;

@InjectPresenter(PreviewUserPresenter.class)
public class PreviewUserActivity extends BaseSkinActivity<PreviewUserContract.Presenter>
        implements PreviewUserContract.PreviewUserView, PreviewUserContract {

    public static final String USER_ID = "user_id";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_preview_user;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        long userId = getIntent().getLongExtra(USER_ID, 0L);
        CustomToast.getInstance().show("userId=" + userId);
    }

    @Override
    protected void initData() {

    }
}
