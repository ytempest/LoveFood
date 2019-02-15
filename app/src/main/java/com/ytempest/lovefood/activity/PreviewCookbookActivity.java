package com.ytempest.lovefood.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.PreviewCookbookContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.presenter.PreviewCookbookPresenter;
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

    public static final String COOK_ID = "cook_id";

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

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_preview_cookbook;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        long cookId = getIntent().getLongExtra(COOK_ID, -1);
        getPresenter().getCookbookInfo(cookId);
    }

    /* MVP View */

    @Override
    public void onGetCookbookInfo(CookbookInfo data) {
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
}