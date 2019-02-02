package com.ytempest.lovefood.fragment;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.CookbookContract;
import com.ytempest.lovefood.presenter.CookbookPresenter;

/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(CookbookPresenter.class)
public class CookbookFragment extends BaseFragment<CookbookContract.Presenter> implements CookbookContract.CookbookView, CookbookContract {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cookbook;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }
}