package com.ytempest.lovefood.mvp.view.activity;

import com.ytempest.baselibrary.base.mvp.MvpFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.contract.PageListContract;
import com.ytempest.lovefood.mvp.presenter.PageListPresenter;

/**
 * @author ytempest
 * @date 2019/3/22
 */
@InjectPresenter(PageListPresenter.class)
public class PageListFragment extends MvpFragment<PageListContract.Presenter> implements PageListContract.PageListView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_page_list;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
