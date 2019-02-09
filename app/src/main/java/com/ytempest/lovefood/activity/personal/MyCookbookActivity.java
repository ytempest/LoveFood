package com.ytempest.lovefood.activity.personal;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.MyCookbookContract;
import com.ytempest.lovefood.presenter.MyCookbookPresenter;

import butterknife.BindView;

@InjectPresenter(MyCookbookPresenter.class)
public class MyCookbookActivity extends BaseSkinActivity<MyCookbookContract.Presenter>
        implements MyCookbookContract.MyCookbookView, MyCookbookContract {

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setTitleText("我的菜谱");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
