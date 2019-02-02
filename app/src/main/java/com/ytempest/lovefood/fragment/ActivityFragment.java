package com.ytempest.lovefood.fragment;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.ActivityContract;
import com.ytempest.lovefood.presenter.ActivityPresenter;

/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(ActivityPresenter.class)
public class ActivityFragment extends BaseFragment<ActivityContract.Presenter> implements ActivityContract.ActivityView, ActivityContract {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }
}