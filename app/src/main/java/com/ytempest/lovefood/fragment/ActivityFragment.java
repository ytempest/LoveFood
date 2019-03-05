package com.ytempest.lovefood.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.activity.CookbookListActivity;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.contract.ActivityContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.presenter.ActivityPresenter;
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
public class ActivityFragment extends BaseFragment<ActivityContract.Presenter> implements ActivityContract.ActivityView, ActivityContract,
        LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnItemClickListener {

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

    }

    /* MVP View */

    @Override
    public void onGetActivityList(DataList<ActivityInfo> data) {
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

    /* Load */

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadActivityList(mPageNum, Config.PAGE_SIZE);
    }
}