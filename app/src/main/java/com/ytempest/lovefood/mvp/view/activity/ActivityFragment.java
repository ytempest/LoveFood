package com.ytempest.lovefood.mvp.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.ytempest.baselibrary.base.mvp.MvpFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.RefreshRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.ActivityContract;
import com.ytempest.lovefood.mvp.presenter.ActivityPresenter;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(ActivityPresenter.class)
public class ActivityFragment extends MvpFragment<ActivityContract.Presenter> implements ActivityContract.ActivityView,
        CommonRecyclerAdapter.OnItemClickListener, LoadRecyclerView.OnLoadMoreListener, RefreshRecyclerView.OnRefreshMoreListener {

    private static final String TAG = "ActivityFragment";

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private int mPageNum = 1;
    private List<ActivityInfo> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<ActivityInfo> mAdapter;
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CommonRecyclerAdapter<ActivityInfo>(
                getActivity(), mDataList, R.layout.item_activity) {
            @Override
            protected void bindViewData(CommonViewHolder holder, ActivityInfo item) {
                String url = RetrofitClient.client().getUrl() + item.getActImageUrl();
                ImageLoaderManager.getInstance().showImage(holder.getView(R.id.iv_cover), url, null);
                holder.setText(R.id.tv_title, item.getActTitle());
                String startTime = DateFormatUtils.formatDate(item.getActStartTime());
                Long finishTime = item.getActFinishTime();
                String finishTimeStr = DateFormatUtils.formatDate(finishTime);
                holder.setText(R.id.tv_time, startTime + " 至 " + finishTimeStr);
                String status = System.currentTimeMillis() > finishTime ? "已结束" : "进行中";
                holder.setText(R.id.tv_status, status);
            }
        };
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        getPresenter().getActivityList(mPageNum, Config.ACTIVITY_PAGE_SIZE);
    }

    /* Click */

    @Override
    public void onItemClick(View view, int position) {
        long actId = mDataList.get(position - 1).getActId();
        ActivityDetailActivity.startActivity(getContext(), actId);
    }

    /* MVP View */

    @Override
    public void onGetActivityList(DataList<ActivityInfo> data) {
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);

        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyItemInserted(lastPosition);
    }

    private void addLoadView(DataList<ActivityInfo> result) {
        long total = result.getTotal();
        if (total > Config.ACTIVITY_PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

    @Override
    public void onRefreshActivityList(DataList<ActivityInfo> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.stopRefresh();
    }

    @Override
    public void onLoadActivityList(DataList<ActivityInfo> data) {
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
        getPresenter().refreshActivityList(mPageNum, Config.ACTIVITY_PAGE_SIZE);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadActivityList(mPageNum, Config.ACTIVITY_PAGE_SIZE);
    }
}