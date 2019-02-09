package com.ytempest.lovefood.activity.personal;

import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.presenter.PreviewUserPresenter;
import com.ytempest.lovefood.util.DateUtils;

import butterknife.BindView;

@InjectPresenter(PreviewUserPresenter.class)
public class PreviewUserActivity extends BaseSkinActivity<PreviewUserContract.Presenter>
        implements PreviewUserContract.PreviewUserView, PreviewUserContract {

    public static final String USER_ID = "user_id";

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.iv_head)
    protected ImageView mHeadIv;

    @BindView(R.id.tv_account)
    protected TextView mAccountTv;

    @BindView(R.id.tv_sex)
    protected TextView mSexTv;

    @BindView(R.id.tv_birth)
    protected TextView mBirthTv;

    @BindView(R.id.tv_phone)
    protected TextView mPhoneTv;

    @BindView(R.id.tv_qq)
    protected TextView mQQTv;

    @BindView(R.id.tv_email)
    protected TextView mEmailTv;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_preview_user;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        long userId = getIntent().getLongExtra(USER_ID, -1);
        if (userId == -1) {
            CustomToast.getInstance().show("数据异常");
            finish();
        }
        getPresenter().requestUserInfo(userId);
        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initData() {

    }

    /* MVP View */

    @Override
    public void onRequestUserInfo(UserInfo data) {
        String url = RetrofitClient.client().getUrl() + data.getUserHeadUrl();
        ImageLoaderManager.getInstance().showImage(mHeadIv, url, null);
        mAccountTv.setText(data.getUserAccount());
        mSexTv.setText(data.getUserSex());
        mBirthTv.setText(DateUtils.format(data.getUserBirth()));

        mPhoneTv.setText(data.getUserPhone());
        mQQTv.setText(data.getUserQQ());
        mEmailTv.setText(data.getUserEmail());
    }
}
