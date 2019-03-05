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
import com.ytempest.lovefood.contract.ActivityContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
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
    private List<String> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<String> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initView() {
        for (int i = 0; i < 6; i++) {
            mDataList.add("String" + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CommonRecyclerAdapter<String>(
                getActivity(), mDataList, R.layout.item_activity) {
            @Override
            protected void bindViewData(CommonViewHolder holder, String item) {

            }
        };
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
    }

    /* Click */

    @Override
    public void onItemClick(View view, int position) {

    }

    /* Load */

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadActivityList(mPageNum, Config.PAGE_SIZE);
    }
}