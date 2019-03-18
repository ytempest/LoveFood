package com.ytempest.lovefood.mvp.view.topic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.dialog.AlertDialog;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.aop.CheckNet;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseComment;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.CommentDetailInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.mvp.contract.TopicDetailContract;
import com.ytempest.lovefood.mvp.presenter.TopicDetailPresenter;
import com.ytempest.lovefood.mvp.view.EditCookbookActivity;
import com.ytempest.lovefood.mvp.view.personal.PreviewUserActivity;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;
import com.ytempest.lovefood.util.JSON;
import com.ytempest.lovefood.util.NumberUtils;
import com.ytempest.lovefood.util.UserHelper;
import com.ytempest.lovefood.widget.PicturesLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@InjectPresenter(TopicDetailPresenter.class)
public class TopicDetailActivity extends BaseSkinActivity<TopicDetailContract.Presenter>
        implements TopicDetailContract.TopicDetailView, TopicDetailContract,
        CommonRecyclerAdapter.OnItemClickListener,
        LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnLongClickListener {

    private static final String TAG = "TopicDetailActivity";

    private static final String KEY_TOPIC_INFO = "topic_info";
    private TopicInfo mTopicInfo;

    public static void startActivity(Context context, TopicInfo topicInfo) {
        String json = JSON.to(topicInfo);
        Intent intent = new Intent(context, TopicDetailActivity.class);
        intent.putExtra(KEY_TOPIC_INFO, json);
        context.startActivity(intent);
    }

    private int mPageNum = 1;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private List<CommentDetailInfo> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<CommentDetailInfo> mAdapter;
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void initTitle() {
        String json = getIntent().getStringExtra(KEY_TOPIC_INFO);
        mTopicInfo = JSON.from(json, TopicInfo.class);

        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(TopicDetailActivity.this));
        mAdapter = new CommonRecyclerAdapter<CommentDetailInfo>(
                TopicDetailActivity.this, mDataList, R.layout.item_comment) {
            @Override
            protected void bindViewData(CommonViewHolder holder, CommentDetailInfo item) {
                String url = RetrofitClient.client().getUrl() + item.getUserHeadUrl();
                ImageView headView = holder.getView(R.id.iv_head);
                ImageLoaderManager.getInstance().showImage(headView, url, null);
                View userView = holder.getView(R.id.ll_user_container);
                userView.setTag(item.getCommentFromUser());
                userView.setOnClickListener(PREVIEW_USER_INFO);
                holder.setText(R.id.tv_name, item.getUserAccount());
                holder.setText(R.id.tv_time, DateFormatUtils.formatTime(item.getCommentTime()));
                holder.setText(R.id.tv_content, item.getCommentContent());
                Long count = item.getReplyCount();
                if (NumberUtils.isEmpty(count)) {
                    holder.setViewVisibility(R.id.tv_comment_count, View.GONE);

                } else {
                    holder.setText(R.id.tv_comment_count, String.format("共%s条回复", count));
                }
            }
        };
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addHeaderView(getTopicHeadView(mRecyclerView, mTopicInfo));
    }

    private View getTopicHeadView(ViewGroup viewGroup, TopicInfo info) {
        View view = LayoutInflater.from(this).inflate(R.layout.view_head_topic_detail, viewGroup, false);
        ImageView headView = view.findViewById(R.id.iv_head);
        TextView nameView = view.findViewById(R.id.tv_name);
        TextView timeView = view.findViewById(R.id.tv_time);
        TextView titleView = view.findViewById(R.id.tv_title);
        TextView contentView = view.findViewById(R.id.tv_content);
        PicturesLayout picturesLayout = view.findViewById(R.id.picture_layout);
        TextView commentView = view.findViewById(R.id.tv_comment_count);

        String url = RetrofitClient.client().getUrl() + info.getUserHeadUrl();
        ImageLoaderManager.getInstance().showImage(headView, url, null);
        nameView.setText(info.getUserAccount());
        timeView.setText(DateFormatUtils.formatTime(info.getTopicPublishTime()));
        titleView.setText(String.format("## %s ##", info.getTopicTitle()));
        contentView.setText(info.getTopicContent());
        picturesLayout.setPictureUrlList(info.getTopicImage());
        long count = info.getCommentCount() != null ? info.getCommentCount() : 0;
        commentView.setText("评论 " + count);
        return view;
    }

    private final View.OnClickListener PREVIEW_USER_INFO = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            long userId = (long) v.getTag();
            PreviewUserActivity.startActivity(TopicDetailActivity.this, userId);
        }
    };

    @Override
    protected void initData() {
        getPresenter().getCommentList(mTopicInfo.getTopicId(), mPageNum, Config.PAGE_SIZE);
    }

    /* Click */

    @CheckNet
    @Override
    public void onItemClick(View view, int position) {

    }

    private AlertDialog mCommentDialog;

    @CheckNet
    @OnClick(R.id.ll_comment)
    protected void onCommentClick(View view) {
        if (mCommentDialog == null) {
            mCommentDialog = getCommentDialog();
        }
        mCommentDialog.show();
    }

    private AlertDialog getCommentDialog() {
        AlertDialog dialog = new AlertDialog.Builder(TopicDetailActivity.this, R.style.comment_dialog)
                .setContentView(R.layout.dialog_comment_frame)
                .addDefaultAnimation()
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .fullWidth()
                .formBottom(true)
                .setCanceledOnTouchOutside(true)
                .create();
        dialog.setOnClickListener(R.id.bt_send, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView contentView = dialog.getView(R.id.et_comment);
                String content = contentView.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    long topicId = mTopicInfo.getTopicId();
                    long fromUser = UserHelper.getInstance().getUserInfo().getUserId();
                    long toUser = mTopicInfo.getUserId();
                    getPresenter().addComment(topicId, content, fromUser, toUser);
                }
            }
        });
        return dialog;
    }

    @Override
    public boolean onItemLongClick(View view, int position) {
        AlertDialog dialog = getDialog();
        View edit = dialog.getView(R.id.tv_edit_cookbook);
        edit.setTag(mDataList.get(position - 1));
        edit.setOnClickListener(EDIT_COOKBOOK_LISTENER);
        dialog.show();
        return true;
    }

    private final View.OnClickListener EDIT_COOKBOOK_LISTENER = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BaseCookbook cookbook = (BaseCookbook) v.getTag();
            EditCookbookActivity.startActivity(TopicDetailActivity.this, cookbook.getCookId());
            mDialog.dismiss();
        }
    };

    private AlertDialog mDialog;

    private AlertDialog getDialog() {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(TopicDetailActivity.this)
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
    public void onGetCommentListSuccess(DataList<CommentDetailInfo> data) {

        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyItemInserted(lastPosition + 1);
    }

    private void addLoadView(DataList<CommentDetailInfo> data) {
        long total = data.getTotal();
        if (total > Config.PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

    @Override
    public void onGetCommentListFail(String msg) {
        // TODO: 2019/03/18 如果评论数为0如何处理
    }

    @Override
    public void onLoadCommentList(DataList<CommentDetailInfo> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());
        mAdapter.notifyItemInserted(lastPosition + 1);
        mRecyclerView.stopLoad();
        if (mPageNum == data.getPageCount()) {
            mRecyclerView.removeLoadViewCreator();
        }
    }


    @Override
    public void onSendCommentSuccess(BaseComment comment) {
        mCommentDialog.dismiss();
        UserInfo user = UserHelper.getInstance().getUserInfo();
        CommentDetailInfo info = new CommentDetailInfo();
        info.setCommentId(-1L);
        info.setCommentFromUser(comment.getCommentFromUser());
        info.setUserHeadUrl(user.getUserHeadUrl());
        info.setUserAccount(user.getUserAccount());
        info.setCommentContent(comment.getCommentContent());
        info.setCommentTime(comment.getCommentTime());
        info.setReplyCount(0L);
        mDataList.add(0, info);
        mAdapter.notifyItemInserted(0);
    }

    /* Load */

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadCommentList(mTopicInfo.getTopicId(), mPageNum, Config.PAGE_SIZE);
    }
}
