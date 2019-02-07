package com.ytempest.lovefood.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.activity.AccountManageActivity;
import com.ytempest.lovefood.activity.PreviewUserActivity;
import com.ytempest.lovefood.contract.PersonalContract;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.presenter.PersonalPresenter;
import com.ytempest.lovefood.util.UserHelper;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(PersonalPresenter.class)
public class PersonalFragment extends BaseFragment<PersonalContract.Presenter> implements PersonalContract.PersonalView, PersonalContract {

    private static final String TAG = "PersonalFragment";

    @BindView(R.id.iv_head)
    protected ImageView mHeadIv;

    @BindView(R.id.tv_account_name)
    protected TextView mAccountTv;

    private UserInfo userInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        userInfo = getPresenter().getUserInfo();
        String url = RetrofitClient.client().getUrl() + userInfo.getUserHeadUrl();
        ImageLoaderManager.getInstance().showImage(mHeadIv, url, null);
        mAccountTv.setText(userInfo.getUserAccount());
    }

    @Override
    protected void initData() {
    }


    /* Click */

    @OnClick(R.id.tv_setting)
    protected void onSettingClick(View view) {

    }


    @OnClick(R.id.iv_head)
    protected void onHeadClick(View view) {
        previewUserInfo(userInfo.getUserId());
    }

    @OnClick(R.id.ll_user)
    protected void onUserClick(View view) {
        previewUserInfo(userInfo.getUserId());
    }

    private void previewUserInfo(long userId) {
        Intent intent = new Intent(mContext, PreviewUserActivity.class);
        intent.putExtra(PreviewUserActivity.USER_ID, userId);
        startActivity(intent);
    }

    @OnClick(R.id.item_cookbook)
    protected void onMyCookbookClick(View view) {

    }

    @OnClick(R.id.item_topic)
    protected void onMyTopicClick(View view) {

    }

    @OnClick(R.id.item_account_manage)
    protected void onAccountManageClick(View view) {
        Intent intent = new Intent(getActivity(), AccountManageActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.item_collection)
    protected void onMyCollectionClick(View view) {

    }

    @OnClick(R.id.item_activity)
    protected void onPartakeActivityClick(View view) {

    }


    @OnClick(R.id.item_help)
    protected void onHelpClick(View view) {

    }


}