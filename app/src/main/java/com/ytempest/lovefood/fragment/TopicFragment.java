package com.ytempest.lovefood.fragment;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.presenter.TopicPresenter;


/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(TopicPresenter.class)
public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.TopicView, TopicContract {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }
}

