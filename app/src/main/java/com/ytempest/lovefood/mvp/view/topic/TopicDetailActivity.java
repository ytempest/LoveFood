package com.ytempest.lovefood.mvp.view.topic;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
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
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.CommentInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.mvp.contract.CookbookListContract;
import com.ytempest.lovefood.mvp.contract.TopicDetailContract;
import com.ytempest.lovefood.mvp.presenter.CookbookListPresenter;
import com.ytempest.lovefood.mvp.presenter.TopicDetailPresenter;
import com.ytempest.lovefood.mvp.view.EditCookbookActivity;
import com.ytempest.lovefood.mvp.view.PreviewCookbookActivity;
import com.ytempest.lovefood.util.CommonUtils;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;
import com.ytempest.lovefood.util.JSON;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(TopicDetailPresenter.class)
public class TopicDetailActivity extends BaseSkinActivity<TopicDetailContract.Presenter>
        implements TopicDetailContract.TopicDetailView, TopicDetailContract,
        CommonRecyclerAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnLongClickListener {

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

//    @BindView(R.id.navigation_view)
//    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private List<CommentInfo> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<CommentInfo> mAdapter;
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();

    private String mGroup;
    private String mType;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_topic_detail;
    }

    @Override
    protected void initTitle() {
        String json = getIntent().getStringExtra(KEY_TOPIC_INFO);
        mTopicInfo = JSON.from(json, TopicInfo.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle("蕾米莉亚");

//        mNavigationView.enableLeftFinish(this);
//        mNavigationView.setTitleText(mType);
    }

    @Override
    protected void initView() {
        for (int i = 0; i < 10; i++) {
            mDataList.add(new CommentInfo());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(TopicDetailActivity.this));
        mAdapter = new CommonRecyclerAdapter<CommentInfo>(
                TopicDetailActivity.this, mDataList, R.layout.item_cook_book) {
            @Override
            protected void bindViewData(CommonViewHolder holder, CommentInfo item) {
            }
        };
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {
//        getPresenter().getCookbookList(mPageNum, Config.PAGE_SIZE, mGroup, mType);
    }

    /* Click */

    @CheckNet
    @Override
    public void onItemClick(View view, int position) {

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

//    @Override
//    public void onGetCookbookList(DataList<BaseCookbook> data) {
//        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
//        mRecyclerView.setOnRefreshMoreListener(this);
//
//        int lastPosition = mDataList.size();
//        mDataList.addAll(data.getList());
//
//        // 添加上拉刷新的View
//        addLoadView(data);
//
//        mAdapter.notifyItemInserted(lastPosition);
//    }

//    @Override
//    public void onFailGetCookbookList(String msg) {
//        TextView tipText = CommonUtils.getTipText(getContext(), mRecyclerView);
//        tipText.setText(msg);
//        mRecyclerView.addHeaderView(tipText);
//    }

//    @Override
//    public void onRefreshCookbookList(DataList<BaseCookbook> data) {
//        mDataList.clear();
//        mDataList.addAll(data.getList());
//
//        // 添加上拉刷新的View
//        addLoadView(data);
//
//        mAdapter.notifyDataSetChanged();
//        mRecyclerView.stopRefresh();
//    }

    private void addLoadView(DataList<BaseCookbook> data) {
        long total = data.getTotal();
        if (total > Config.PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

//    @Override
//    public void onLoadCookbookList(DataList<BaseCookbook> data) {
//        int lastPosition = mDataList.size();
//        mDataList.addAll(data.getList());
//        mAdapter.notifyItemInserted(lastPosition + 1);
//        mRecyclerView.stopLoad();
//        if (mPageNum == data.getPageCount()) {
//            mRecyclerView.removeLoadViewCreator();
//        }
//    }

    /* Load */

    @Override
    public void onRefresh() {
        mPageNum = 1;
//        getPresenter().refreshCookbookList(mPageNum, Config.PAGE_SIZE, mGroup, mType);
    }

    @Override
    public void onLoad() {
        mPageNum++;
//        getPresenter().loadCookbookList(mPageNum, Config.PAGE_SIZE, mGroup, mType);
    }

}
