package com.ytempest.lovefood.mvp.view.topic;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.RefreshRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.view.personal.PreviewUserActivity;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.mvp.contract.TopicContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.mvp.presenter.TopicPresenter;
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
    private CommonRecyclerAdapter<TopicInfo> mAdapter;
    private List<TopicInfo> mDataList = new ArrayList<>();
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
        mAdapter = new CommonRecyclerAdapter<TopicInfo>(
                getContext(), mDataList, R.layout.item_topic_view) {
            @Override
            protected void bindViewData(CommonViewHolder holder, TopicInfo item) {
                String url = RetrofitClient.client().getUrl() + item.getUserHeadUrl();
                ImageLoaderManager.getInstance().showImage(holder.getView(R.id.iv_head), url, null);
                View userView = holder.getView(R.id.ll_user_container);
                userView.setTag(item.getUserId());
                userView.setOnClickListener(PREVIEW_USER_LISTENER);
                holder.setText(R.id.tv_name, item.getUserAccount());
                holder.setText(R.id.tv_time, DateFormatUtils.formatTime(item.getTopicPublishTime()));

                TextView titleView = holder.getView(R.id.tv_title);
                titleView.setText(String.format("## %s ##", item.getTopicTitle()));
                TextView contentView = holder.getView(R.id.tv_content);
                contentView.setText(item.getTopicContent());
                titleView.setTag(item.getTopicId());
                titleView.setOnClickListener(OPEN_TOPIC_DETAIL_LISTENER);
                contentView.setTag(item.getTopicId());
                contentView.setOnClickListener(OPEN_TOPIC_DETAIL_LISTENER);

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

    private final View.OnClickListener PREVIEW_USER_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            long userId = (long) v.getTag();
            PreviewUserActivity.startActivity(getContext(), userId);
        }
    };

    private final View.OnClickListener OPEN_TOPIC_DETAIL_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            long topicId = (long) v.getTag();
            // TODO: 2019/03/03 等待加入打开话题详情页的逻辑
            CustomToast.getInstance().show("topicId=" + topicId);
        }
    };

    private final PicturesLayout.Callback SHOW_PICTURE_CALLBACK = new PicturesLayout.Callback() {
        @Override
        public void onPictureClick(ImageView i, String url, List<ImageView> imageGroupList, List<TopicInfo.Image> urlList) {
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
        getPresenter().getTopicList(mPageNum, Config.PAGE_SIZE);
    }

    /* Click */

    @Override
    public void onItemClick(View view, int position) {

    }

    /* MVP View */

    @Override
    public void onGetTopicList(DataList<TopicInfo> result) {
        int lastPosition = mDataList.size();
        mDataList.addAll(result.getList());

        // 添加上拉刷新的View
        addLoadView(result);

        mAdapter.notifyItemInserted(lastPosition);
    }

    private void addLoadView(DataList<TopicInfo> result) {
        long total = result.getTotal();
        if (total > Config.PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

    @Override
    public void onRefreshTopicList(DataList<TopicInfo> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.stopRefresh();
    }

    @Override
    public void onLoadCookbookList(DataList<TopicInfo> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());
        mAdapter.notifyItemInserted(lastPosition + 1);
        mRecyclerView.stopLoad();
        if (mPageNum == data.getPageCount()) {
            mRecyclerView.removeLoadViewCreator();
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

