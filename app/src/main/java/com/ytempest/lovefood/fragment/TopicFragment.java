package com.ytempest.lovefood.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.http.data.TopicResult;
import com.ytempest.lovefood.presenter.TopicPresenter;
import com.ytempest.lovefood.util.DrawUtils;
import com.ytempest.lovefood.widget.PicturesLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;


/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(TopicPresenter.class)
public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.TopicView, TopicContract {

    private static final String TAG = "TopicFragment";

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    private List<TopicResult> mTopicList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView() {
        mTopicList = new ArrayList<>();
        TopicResult result = new TopicResult();
        result.setPictureList(new ArrayList<String>());
        for (int i = 0; i < 10; i++) {
            TopicResult topicResult = new TopicResult();
            List<String> pictureList = new ArrayList<>();
            int k = i;
            for (; k > 0; k--) {
                pictureList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2136782320,914585746&fm=26&gp=0.jpg");
            }
            topicResult.setPictureList(pictureList);
            mTopicList.add(topicResult);
        }


        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new CommonRecyclerAdapter<TopicResult>(mContext, mTopicList, R.layout.item_topic_view) {
            @Override
            protected void bindViewData(CommonViewHolder holder, TopicResult item) {
                PicturesLayout picturesLayout = (PicturesLayout) holder.getView(R.id.picture_layout);
//                print(picturesLayout);
                picturesLayout.setPictureUrlList(item.getPictureList());
                picturesLayout.setCallback(new PicturesLayout.Callback() {
                    @Override
                    public void onPictureClick(ImageView i, String url, List<ImageView> imageGroupList, List<String> urlList) {
                        if (mImageDialog == null) {
                            mImageDialog = new AlertDialog.Builder(mContext)
                                    .setContentView(R.layout.dialog_show_picture)
                                    .setWidthAndHeight(DrawUtils.getScreenWidth(getContext()), DrawUtils.getScreenHeight(getContext()))
                                    .fullWidth()
                                    .create();
                            mImageDialog.setOnClickListener(R.id.container, DISMISS_IMAGE_DIALOG_LISTENER);
                        }

                        ImageLoaderManager.getInstance().showImage(mImageDialog.getView(R.id.iv_show_picture), url, null);
                        mImageDialog.show();
                    }
                });

            }
        });
    }

    private AlertDialog mImageDialog;

    private View.OnClickListener DISMISS_IMAGE_DIALOG_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mImageDialog.dismiss();
        }
    };

    @Override
    protected void initData() {

    }
}

