package com.ytempest.lovefood.mvp.view.topic;

import android.content.Intent;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.http.RetrofitUtils;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.presenter.TopicReleasePresenter;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelectActivity;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelector;
import com.ytempest.lovefood.util.DataUtils;
import com.ytempest.lovefood.util.UserHelper;
import com.ytempest.lovefood.widget.PictureFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;

@InjectPresenter(TopicReleasePresenter.class)
public class TopicReleaseActivity extends BaseSkinActivity<TopicReleaseContract.Presenter>
        implements TopicReleaseContract.TopicReleaseView {

    private static final String TAG = "TopicReleaseActivity";

    private static final int REQUEST_CODE = 0x11;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.picture_container)
    protected PictureFlowLayout mPictureContainer;

    @BindView(R.id.tv_title)
    protected EditText mTitleTv;

    @BindView(R.id.tv_content)
    protected EditText mContentTv;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topic_release;
    }

    @Override
    protected void initTitle() {
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseTopic();
            }
        });
    }

    @Override
    protected void initView() {
        mPictureContainer.setSelectPictureListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageSelector.create()
                        .count(mPictureContainer.getMaxCapacity())
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

    private void releaseTopic() {
        String title = mTitleTv.getText().toString();
        if (TextUtils.isEmpty(title)) {
            CustomToast.getInstance().show("标题不能为空");
        }

        String content = mContentTv.getText().toString();
        if (TextUtils.isEmpty(content)) {
            CustomToast.getInstance().show("内容不能为空");
        }

        Map<String, RequestBody> partMap;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            partMap = new ArrayMap<>(mPictureContainer.getMaxCapacity() + 3);
        } else {
            partMap = new HashMap<>(mPictureContainer.getMaxCapacity() + 3);
        }

        if (mPictureContainer.getCapacity() > 0) {
            ArrayList<String> pictureList = mPictureContainer.getPictureList();
            for (int i = 0, len = DataUtils.getSize(pictureList); i < len; i++) {
                File picture = new File(pictureList.get(i));
                partMap.put(RetrofitUtils.toFileKey("topicImage" + i, picture.getName()),
                        RetrofitUtils.createBodyFromFile(picture));
            }
        }
        Long userId = UserHelper.getInstance().getUserInfo().getUserId();
        partMap.put("userId", RetrofitUtils.createBodyFromString(String.valueOf(userId)));
        partMap.put("topicTitle", RetrofitUtils.createBodyFromString(title));
        partMap.put("topicContent", RetrofitUtils.createBodyFromString(content));

        getPresenter().releaseTopic(partMap);
    }

    /* MVP View */

    @Override
    public void onReleaseTopicSuccess() {
        TopicReleaseActivity.this.finish();
    }
}
