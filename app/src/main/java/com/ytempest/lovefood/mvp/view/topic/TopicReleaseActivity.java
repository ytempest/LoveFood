package com.ytempest.lovefood.mvp.view.topic;

import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.presenter.TopicReleasePresenter;
import com.ytempest.lovefood.widget.PictureFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(TopicReleasePresenter.class)
public class TopicReleaseActivity extends BaseSkinActivity<TopicReleaseContract.Presenter>
        implements TopicReleaseContract.TopicReleaseView {

    private static final String TAG = "TopicReleaseActivity";

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.picture_container)
    protected PictureFlowLayout mPictureContainer;

    @BindView(R.id.iv_add_picture)
    protected ImageView mAddPicture;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topic_release;
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initView() {
        List<String> paths = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            paths.add("" + i);
        }
        mPictureContainer.addPictureList(paths);
    }

    @Override
    protected void initData() {
    }

}
