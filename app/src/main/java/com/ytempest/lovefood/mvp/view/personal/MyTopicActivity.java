package com.ytempest.lovefood.mvp.view.personal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.RefreshRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.aop.CheckNet;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.mvp.contract.MyTopicContract;
import com.ytempest.lovefood.mvp.presenter.MyTopicPresenter;
import com.ytempest.lovefood.mvp.view.topic.TopicDetailActivity;
import com.ytempest.lovefood.mvp.view.topic.TopicReleaseActivity;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;
import com.ytempest.lovefood.util.DrawUtils;
import com.ytempest.lovefood.util.NumberUtils;
import com.ytempest.lovefood.widget.PicturesLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(MyTopicPresenter.class)
public class MyTopicActivity extends BaseSkinActivity<MyTopicContract.Presenter>
        implements MyTopicContract.MyTopicView,
        CommonRecyclerAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnLongClickListener {

    private static final String TAG = "MyTopicActivity";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyTopicActivity.class);
        context.startActivity(intent);
    }

    private int mPageNum = 1;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private List<TopicInfo> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<TopicInfo> mAdapter;
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setTitleText("我的话题");
        mNavigationView.setRightText("发布");
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicReleaseActivity.startActivity(getContext());
            }
        });
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyTopicActivity.this));
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
    }

    @Override
    protected void initData() {
        mAdapter = new CommonRecyclerAdapter<TopicInfo>(
                getContext(), mDataList, R.layout.item_topic_view) {
            @Override
            protected void bindViewData(CommonViewHolder holder, TopicInfo item) {
                String url = RetrofitClient.client().getUrl() + item.getUserHeadUrl();
                ImageLoaderManager.getInstance().showImage(holder.getView(R.id.iv_head), url, null);
                View userView = holder.getView(R.id.ll_user_container);
                userView.setTag(item.getUserId());
                holder.setText(R.id.tv_name, item.getUserAccount());
                holder.setText(R.id.tv_time, DateFormatUtils.formatTime(item.getTopicPublishTime()));

                TextView titleView = holder.getView(R.id.tv_title);
                titleView.setText(String.format("## %s ##", item.getTopicTitle()));
                TextView contentView = holder.getView(R.id.tv_content);
                contentView.setText(item.getTopicContent());

                holder.getView(R.id.ll_container).setTag(item);
                holder.getView(R.id.ll_container).setOnClickListener(OPEN_TOPIC_DETAIL_LISTENER);

                PicturesLayout picturesLayout = holder.getView(R.id.picture_layout);
                picturesLayout.setPictureUrlList(item.getTopicImage());
                picturesLayout.setCallback(SHOW_PICTURE_CALLBACK);
                holder.setText(R.id.tv_comment, NumberUtils.getLong(item.getCommentCount()) + "评论");
            }
        };
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        getPresenter().getMyTopicList(mPageNum, Config.PAGE_SIZE);
    }

    private final View.OnClickListener OPEN_TOPIC_DETAIL_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TopicInfo info = (TopicInfo) v.getTag();
            TopicDetailActivity.startActivity(getContext(), info);
        }
    };

    private AlertDialog mImageDialog;

    private final PicturesLayout.Callback SHOW_PICTURE_CALLBACK = new PicturesLayout.Callback() {
        @Override
        public void onPictureClick(ImageView i, String url, List<ImageView> imageGroupList, List<TopicInfo.Image> urlList) {
            if (mImageDialog == null) {
                mImageDialog = new AlertDialog.Builder(getContext())
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

    /* Click */

    @CheckNet
    @Override
    public void onItemClick(View view, int position) {
        TopicInfo topicInfo = mDataList.get(position - 1);
        TopicDetailActivity.startActivity(this, topicInfo);
    }

    @Override
    public boolean onItemLongClick(View view, int position) {
        // TODO  heqidu: 话题长按点击
        CustomToast.getInstance().show("You long click " + position);
        return true;
    }

    private final View.OnClickListener EDIT_TOPIC_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO  heqidu: 编辑话题
//            BaseCookbook cookbook = (BaseCookbook) v.getTag();
//            EditCookbookActivity.startActivity(MyTopicActivity.this, cookbook.getCookId());
            mDialog.dismiss();
        }
    };

    private AlertDialog mDialog;

    private AlertDialog getDialog() {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(MyTopicActivity.this)
                    .setContentView(R.layout.dialog_edit_cookbook)
                    .addDefaultAnimation()
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setCanceledOnTouchOutside(true)
                    .create();

        }
        return mDialog;
    }


    /* MVP View */

    @Override
    public void onGetTopicList(DataList<TopicInfo> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        mAdapter.notifyItemInserted(lastPosition);
    }

    @Override
    public void onRefreshCookbookList(DataList<TopicInfo> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());

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
