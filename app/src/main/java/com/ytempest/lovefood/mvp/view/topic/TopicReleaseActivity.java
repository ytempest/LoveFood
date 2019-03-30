package com.ytempest.lovefood.mvp.view.topic;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.presenter.TopicReleasePresenter;
import com.ytempest.lovefood.util.Config;

import butterknife.BindView;

@InjectPresenter(TopicReleasePresenter.class)
public class TopicReleaseActivity extends BaseSkinActivity<TopicReleaseContract.Presenter>
        implements TopicReleaseContract.TopicReleaseView {

    private static final String TAG = "TopicReleaseActivity";

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topic_release;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

}
