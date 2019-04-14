package com.ytempest.lovefood.mvp.view.personal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
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
import com.ytempest.lovefood.http.data.BaseActivityInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.MyPartakeContract;
import com.ytempest.lovefood.mvp.presenter.MyPartakePresenter;
import com.ytempest.lovefood.mvp.view.activity.ActivityDetailActivity;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(MyPartakePresenter.class)
public class MyPartakeActivity extends BaseSkinActivity<MyPartakeContract.Presenter>
        implements MyPartakeContract.MyPartakeView,
        CommonRecyclerAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener {

    private static final String TAG = "MyPartakeActivity";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyPartakeActivity.class);
        context.startActivity(intent);
    }

    private int mPageNum = 1;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private List<BaseActivityInfo> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<BaseActivityInfo> mAdapter;
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setTitleText("参与的活动");
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyPartakeActivity.this));
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
        mAdapter = new CommonRecyclerAdapter<BaseActivityInfo>(
                MyPartakeActivity.this, mDataList, R.layout.item_partake_activity) {
            @Override
            protected void bindViewData(CommonViewHolder holder, BaseActivityInfo item) {
                ImageView pictureView = holder.getView(R.id.iv_picture);
                String url = RetrofitClient.client().getUrl() + item.getActImageUrl();
                ImageLoaderManager.getInstance().showImage(pictureView, url, null);
                holder.setText(R.id.tv_title, item.getActTitle());
                holder.setText(R.id.tv_desc, item.getActDesc());
                String date = DateFormatUtils.formatDate(item.getActStartTime())
                        + " 至 " + DateFormatUtils.formatDate(item.getActFinishTime());
                holder.setText(R.id.tv_time, date);
                String status = System.currentTimeMillis() > item.getActFinishTime() ? "已结束" : "进行中";
                holder.setText(R.id.tv_status, status);
            }
        };
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        getPresenter().getPartakeList(mPageNum, Config.PAGE_SIZE);
    }

    /* Click */

    @CheckNet
    @Override
    public void onItemClick(View view, int position) {
        BaseActivityInfo cookbook = mDataList.get(position - 1);
        long actId = cookbook.getActId();
        ActivityDetailActivity.startActivity(getContext(), actId);
    }


    /* MVP View */

    @Override
    public void onGetPartakeList(DataList<BaseActivityInfo> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyItemInserted(lastPosition);
    }

    @Override
    public void onRefreshPartakeListSuccess(DataList<BaseActivityInfo> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.stopRefresh();
    }

    @Override
    public void onRefreshPartakeListFail() {
        mDataList.clear();
        mRecyclerView.stopRefresh();
    }

    private void addLoadView(DataList<BaseActivityInfo> data) {
        long total = data.getTotal();
        if (total > Config.PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

    @Override
    public void onLoadPartakeListSuccess(DataList<BaseActivityInfo> data) {
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
        getPresenter().refreshPartakeList(mPageNum, Config.PAGE_SIZE);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadPartakeList(mPageNum, Config.PAGE_SIZE);
    }

}
