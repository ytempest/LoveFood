package com.ytempest.lovefood.mvp.view.topic;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.callback.Callback;
import com.ytempest.lovefood.http.RetrofitUtils;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.presenter.TopicReleasePresenter;
import com.ytempest.lovefood.mvp.view.PermissionActivity;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelectActivity;
import com.ytempest.lovefood.mvp.view.imageSelect.ImageSelector;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DataUtils;
import com.ytempest.lovefood.util.UserHelper;
import com.ytempest.lovefood.widget.ImageLoader;
import com.ytempest.lovefood.widget.PictureFlowLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.RequestBody;

@InjectPresenter(TopicReleasePresenter.class)
public class TopicReleaseActivity extends PermissionActivity<TopicReleaseContract.Presenter>
        implements TopicReleaseContract.TopicReleaseView {

    private static final String TAG = "TopicReleaseActivity";
    private static final int REQUEST_CODE = 0x11;
    private static final int CODE_PERMISSION_READ = 0x000111;


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TopicReleaseActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.picture_container)
    protected PictureFlowLayout mPictureContainer;

    @BindView(R.id.tv_title)
    protected EditText mTitleTv;

    @BindView(R.id.tv_content)
    protected EditText mContentTv;

    private File mCacheImageDir;

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
                onSelectImage();
            }
        });

        mPictureContainer.setImageLoader(new ImageLoader() {
            @Override
            public void onLoad(ImageView imageView, String imagePath) {
                ImageLoaderManager.getInstance().showImage(imageView, imagePath, null);
            }
        });
    }

    @Override
    protected void initData() {
        mCacheImageDir = new File(Config.EXTERNAL_CACHE_TOPIC_IMAGE_DIR);
        if (!mCacheImageDir.exists()) {
            mCacheImageDir.mkdir();
        }
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

    /* click */

    private void releaseTopic() {
        String title = mTitleTv.getText().toString();
        if (TextUtils.isEmpty(title)) {
            CustomToast.getInstance().show("标题不能为空");
            return;
        }

        String content = mContentTv.getText().toString();
        if (TextUtils.isEmpty(content)) {
            CustomToast.getInstance().show("内容不能为空");
            return;
        }

        Map<String, RequestBody> partMap;
        int pictureCount = mPictureContainer.getCapacity();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            partMap = new ArrayMap<>(pictureCount + 3);
        } else {
            partMap = new HashMap<>(pictureCount + 3);
        }

        if (pictureCount > 0) {
            ArrayList<String> pictureList = mPictureContainer.getPictureList();
            for (int i = 0, len = DataUtils.getSize(pictureList); i < len; i++) {
                File picture = getPictureFile(pictureList.get(i));
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

    private File getPictureFile(String path) {
        // TODO: 2019/04/09 加入话题图片压缩功能
//        String destPath = mCacheImageDir.getAbsolutePath() + File.separator + new File(path).getName();
//        ImageUtils.compressImage(path, destPath);
        return new File(path);
    }

    private void onSelectImage() {
        requestPermission(CODE_PERMISSION_READ,
                "需要获取手机的读写权限",
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}
                , new Callback<Boolean>() {
                    @Override
                    public void onCall(Boolean result) {
                        if (result) {
                            ImageSelector.create()
                                    .count(mPictureContainer.getMaxCapacity())
                                    .origin(mPictureContainer.getPictureList())
                                    .showCamera(false)
                                    .start(TopicReleaseActivity.this, REQUEST_CODE);

                        } else {
                            CustomToast.getInstance().show("请授予对手机读写的权限");
                        }
                    }
                });
    }

    /* MVP View */

    @Override
    public void onReleaseTopicSuccess() {
        TopicReleaseActivity.this.finish();
    }
}
