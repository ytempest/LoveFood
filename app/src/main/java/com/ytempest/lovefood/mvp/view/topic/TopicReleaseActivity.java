package com.ytempest.lovefood.mvp.view.topic;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.presenter.TopicReleasePresenter;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelectActivity;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelector;
import com.ytempest.lovefood.widget.PictureFlowLayout;

import butterknife.BindView;

@InjectPresenter(TopicReleasePresenter.class)
public class TopicReleaseActivity extends BaseSkinActivity<TopicReleaseContract.Presenter>
        implements TopicReleaseContract.TopicReleaseView {

    private static final String TAG = "TopicReleaseActivity";

    private static final int REQUEST_CODE = 0x11;

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
        mPictureContainer.setSelectPictureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelector.create()
                        .count(mPictureContainer.getCapacity())
                        .origin(mPictureContainer.getPictureList())
                        .showCamera(false)
                        .start(TopicReleaseActivity.this, REQUEST_CODE);

            }
        });
        mPictureContainer.setImageLoader(new PictureFlowLayout.ImageLoader() {
            @Override
            public void onLoad(ImageView imageView, String imagePath) {
                ImageLoaderManager.getInstance().showImage(imageView, imagePath, null);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                mPictureContainer.addPictureList(
                        data.getStringArrayListExtra(ImageSelectActivity.EXTRA_RESULT));
            }
        }

    }

}
