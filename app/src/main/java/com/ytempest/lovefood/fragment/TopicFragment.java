package com.ytempest.lovefood.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.baselibrary.view.recyclerview.division.DividerItemDecoration;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.http.data.TopicResult;
import com.ytempest.lovefood.presenter.TopicPresenter;
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
        Random random = new Random();

        mTopicList = new ArrayList<>();
        TopicResult result = new TopicResult();
        result.setPictureList(new ArrayList<String>());
        mTopicList.add(result);
        for (int i = 0; i < 10; i++) {
            TopicResult topicResult = new TopicResult();
            List<String> pictureList = new ArrayList<>();
            int k = random.nextInt(10);
            for (; k > 0; k--) {
                pictureList.add("http://img.my.csdn.net/uploads/201701/06/1483664940_9893.jpg");
            }
            topicResult.setPictureList(pictureList);
            mTopicList.add(topicResult);
        }


        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext));
        mRecyclerView.setAdapter(new CommonRecyclerAdapter<TopicResult>(mContext, mTopicList, R.layout.item_topic_view) {
            @Override
            protected void bindViewData(CommonViewHolder holder, TopicResult item) {
                PicturesLayout picturesLayout = (PicturesLayout) holder.getView(R.id.picture_layout);
//                print(picturesLayout);
                picturesLayout.setPictureUrlList(item.getPictureList());
                picturesLayout.setCallback(new PicturesLayout.Callback() {
                    @Override
                    public void onPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList) {
                        AlertDialog dialog = new AlertDialog.Builder(mContext)
                                .setContentView(R.layout.dialog_show_picture)
                                .fullWidth()
                                .show();
                        Window mWindow = dialog.getWindow();
                        WindowManager.LayoutParams mParams = mWindow.getAttributes();
                        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                        mParams.height = getResources().getDisplayMetrics().heightPixels / 2;

                        ((ImageView) dialog.getView(R.id.iv_show_picture)).setImageResource(R.drawable.default_head);

                    }
                });

            }
        });
    }

    @Override
    protected void initData() {

    }
}

