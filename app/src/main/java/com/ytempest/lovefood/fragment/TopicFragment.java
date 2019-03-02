package com.ytempest.lovefood.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.RefreshRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.TopicResult;
import com.ytempest.lovefood.presenter.TopicPresenter;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;
import com.ytempest.lovefood.util.DrawUtils;
import com.ytempest.lovefood.util.NumberUtils;
import com.ytempest.lovefood.widget.PicturesLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(TopicPresenter.class)
public class TopicFragment extends BaseFragment<TopicPresenter> implements TopicContract.TopicView, TopicContract,
        RefreshRecyclerView.OnRefreshMoreListener, CommonRecyclerAdapter.OnItemClickListener, LoadRecyclerView.OnLoadMoreListener {

    private static final String TAG = "TopicFragment";

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private int mPageNum = 1;
    private CommonRecyclerAdapter<TopicResult.ImageList> mAdapter;
    private List<TopicResult.ImageList> mDataList = new ArrayList<>();
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
        mAdapter = new CommonRecyclerAdapter<TopicResult.ImageList>(
                getContext(), mDataList, R.layout.item_topic_view) {
            @Override
            protected void bindViewData(CommonViewHolder holder, TopicResult.ImageList item) {
                String url = RetrofitClient.client().getUrl() + item.getUserHeadUrl();
                ImageLoaderManager.getInstance().showImage(holder.getView(R.id.iv_head), url, null);
                holder.setText(R.id.tv_name, item.getUserAccount());
                holder.setText(R.id.tv_time, DateFormatUtils.formatTime(item.getTopicPublishTime()));
                holder.setText(R.id.tv_title, String.format("## %s ##", item.getTopicTitle()));
                holder.setText(R.id.tv_content, item.getTopicContent());
                PicturesLayout picturesLayout = holder.getView(R.id.picture_layout);
                picturesLayout.setPictureUrlList(item.getTopicImage());
                picturesLayout.setCallback(SHOW_PICTURE_CALLBACK);
                holder.setText(R.id.tv_comment, NumberUtils.getLong(item.getCommentCount()) + "评论");

                mAdapter.setOnItemClickListener(TopicFragment.this);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    private AlertDialog mImageDialog;

    private final PicturesLayout.Callback SHOW_PICTURE_CALLBACK = new PicturesLayout.Callback() {
        @Override
        public void onPictureClick(ImageView i, String url, List<ImageView> imageGroupList, List<TopicResult.ImageList.Image> urlList) {
            if (mImageDialog == null) {
                mImageDialog = new AlertDialog.Builder(mContext)
                        .setContentView(R.layout.dialog_show_picture)
                        .setWidthAndHeight(DrawUtils.getScreenWidth(getContext()), DrawUtils.getScreenHeight(getContext()))
                        .fullWidth()
                        .create();
                mImageDialog.setOnClickListener(R.id.container, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImageDialog.dismiss();
                    }
                });
            }

            ImageLoaderManager.getInstance().showImage(mImageDialog.getView(R.id.iv_show_picture), url, null);
            mImageDialog.show();
        }
    };

    @Override
    protected void initData() {
        getPresenter().loadTopicList(mPageNum, Config.PAGE_SIZE);
    }

    /* Click */

    @Override
    public void onItemClick(View view, int position) {

    }

    /* MVP View */

    @Override
    public void onGetTopicList(TopicResult result) {
        int lastPosition = mDataList.size();
        mDataList.addAll(result.getList());

        // 添加上拉刷新的View
        addLoadView(result);

        mAdapter.notifyItemInserted(lastPosition);
    }

    private void addLoadView(TopicResult result) {
        long total = result.getTotal() == null ? 0 : result.getTotal();
        if (total > Config.PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

  /* Load */

    @Override
    public void onRefresh() {
        mPageNum = 1;
        getPresenter().refreshTopicList(mPageNum, Config.PAGE_SIZE);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadTopicList(mPageNum, Config.PAGE_SIZE);
    }

}

