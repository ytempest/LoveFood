package com.ytempest.lovefood.activity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.PreviewCookbookContract;
import com.ytempest.lovefood.presenter.PreviewCookbookPresenter;
import com.ytempest.lovefood.widget.AmountView;

import butterknife.BindView;

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

    @BindView(R.id.av_main)
    protected AmountView mMainView;

    @BindView(R.id.av_acc)
    protected AmountView mAccView;

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
        mMainView.setMainData(null);
        mAccView.setAccData(null);
    }
}