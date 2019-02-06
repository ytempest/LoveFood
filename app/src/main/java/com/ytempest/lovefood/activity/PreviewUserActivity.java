package com.ytempest.lovefood.activity;

import android.view.View;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.presenter.PreviewUserPresenter;

import butterknife.BindView;

@InjectPresenter(PreviewUserPresenter.class)
public class PreviewUserActivity extends BaseSkinActivity<PreviewUserContract.Presenter>
        implements PreviewUserContract.PreviewUserView, PreviewUserContract {

    public static final String USER_ID = "user_id";

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

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

        mNavigationView.setTitleText("User info");
        mNavigationView.enableLeftFinish(this);

        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.getInstance().show("right");
            }
        });

    }
}
