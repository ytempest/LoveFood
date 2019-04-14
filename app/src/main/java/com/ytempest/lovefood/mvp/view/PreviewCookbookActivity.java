package com.ytempest.lovefood.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.aop.CheckNet;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.mvp.contract.PreviewCookbookContract;
import com.ytempest.lovefood.mvp.presenter.PreviewCookbookPresenter;
import com.ytempest.lovefood.util.UserHelper;
import com.ytempest.lovefood.widget.AmountView;
import com.ytempest.lovefood.widget.ProcedureView;
import com.ytempest.lovefood.widget.TitleView;

import butterknife.BindView;
import butterknife.BindViews;

/**
 * @author ytempest
 * @date 2019/2/10
 */
@InjectPresenter(PreviewCookbookPresenter.class)
public class PreviewCookbookActivity extends BaseSkinActivity<PreviewCookbookContract.Presenter>
        implements PreviewCookbookContract.PreviewCookbookView, PreviewCookbookContract {

    private static final String USER_ID = "user_id";
    private static final String COOK_ID = "cook_id";
    private static final String ENABLE_COLLECTION = "enable_collection";

    public static void startActivity(Context context, long userId, long cookId) {
        startActivity(context, userId, cookId, true);
    }

    public static void startActivity(Context context, long userId, long cookId, boolean enableCollection) {
        Intent intent = new Intent(context, PreviewCookbookActivity.class);
        intent.putExtra(USER_ID, userId);
        intent.putExtra(COOK_ID, cookId);
        intent.putExtra(ENABLE_COLLECTION, enableCollection);
        context.startActivity(intent);
    }

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.iv_product)
    protected ImageView mProductPhotoIv;

    @BindView(R.id.tv_name)
    protected TextView mNameTv;

    @BindView(R.id.iv_user_head)
    protected ImageView mUserHeadIv;

    @BindView(R.id.tv_user_account)
    protected TextView mUserAccountTv;

    @BindView(R.id.tv_collection_count)
    protected TextView mCollectionCountTv;

    @BindView(R.id.tv_desc)
    protected TextView mDescTv;

    @BindView(R.id.av_main)
    protected AmountView mMainView;

    @BindView(R.id.av_acc)
    protected AmountView mAccView;

    @BindView(R.id.procedure_view)
    protected ProcedureView mProcedureView;

    @BindViews({R.id.tv_title_introduce, R.id.tv_title_main, R.id.tv_title_acc, R.id.tv_title_step})
    protected TitleView[] mTitleViews;

    private long mCookId;
    private long mUserId;
    private boolean enableCollection;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_preview_cookbook;
    }

    @Override
    protected void initTitle() {
        mCookId = getIntent().getLongExtra(COOK_ID, -1);
        boolean enableCollection = getIntent().getBooleanExtra(ENABLE_COLLECTION, false);
        long cookbookUserId = getIntent().getLongExtra(USER_ID, -1);
        mUserId = UserHelper.getInstance().getUserInfo().getUserId();
        this.enableCollection = enableCollection && mUserId != cookbookUserId;
        if (mCookId == -1) {
            CustomToast.getInstance().show("数据异常");
            finish();
        }

        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        if (enableCollection) {
            getPresenter().getCookbookInfo(mCookId, mUserId);

        } else {
            getPresenter().getCookbookInfo(mCookId);
        }
    }

    /* MVP View */

    @Override
    public void onGetCookbookInfo(CookbookInfo data, Boolean isCollection) {
        if (isCollection != null) {
            int iconId = isCollection ?
                    R.drawable.icon_collection_select : R.drawable.icon_collection_normal;
            mNavigationView.setRightIcon(iconId);
            mNavigationView.setRightClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    collectionCookbook();
                }
            });
        }

        mNavigationView.setTitleText(data.getCookTitle());

        String productUrl = RetrofitClient.client().getUrl() + data.getCookImageUrl();
        ImageLoaderManager.getInstance().showImage(mProductPhotoIv, productUrl, null);

        mNameTv.setText(data.getCookTitle());

        String userHeadUrl = RetrofitClient.client().getUrl() + data.getUserHeadUrl();
        ImageLoaderManager.getInstance().showImage(mUserHeadIv, userHeadUrl, null);

        mUserAccountTv.setText(data.getUserAccount());

        long count = data.getCollectCount() != null ? data.getCollectCount() : 0;
        mCollectionCountTv.setText(String.format("%s人收藏", count));

        for (TitleView titleView : mTitleViews) {
            titleView.setVisibility(View.VISIBLE);
        }

        mDescTv.setText(data.getCookDesc());

        mMainView.setMainData(data.getMainList(), false);

        mAccView.setAccData(data.getAccList(), false);

        mProcedureView.setData(data.getProceList(), false);
    }


    @Override
    public void onCollectionCookbook(boolean isCollection) {
        int iconId = isCollection ?
                R.drawable.icon_collection_select : R.drawable.icon_collection_normal;
        mNavigationView.setRightIcon(iconId);
    }

    @CheckNet
    private void collectionCookbook() {
        getPresenter().collectionCookbook(mUserId, mCookId);
    }
}