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
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.MyCollectionContract;
import com.ytempest.lovefood.mvp.presenter.MyCollectionPresenter;
import com.ytempest.lovefood.mvp.view.PreviewCookbookActivity;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(MyCollectionPresenter.class)
public class MyCollectionActivity extends BaseSkinActivity<MyCollectionContract.Presenter>
        implements MyCollectionContract.MyCollectionView,
        CommonRecyclerAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener {

    private static final String TAG = "MyCollectionActivity";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        context.startActivity(intent);
    }

    private int mPageNum = 1;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private List<BaseCookbook> mDataList = new ArrayList<>();
    private CommonRecyclerAdapter<BaseCookbook> mAdapter;
    private final DefaultLoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setTitleText("我的收藏");
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyCollectionActivity.this));
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
        mAdapter = new CommonRecyclerAdapter<BaseCookbook>(
                MyCollectionActivity.this, mDataList, R.layout.item_cook_book) {
            @Override
            protected void bindViewData(CommonViewHolder holder, BaseCookbook item) {
                ImageView pictureView = holder.getView(R.id.iv_picture);
                String url = RetrofitClient.client().getUrl() + item.getCookImageUrl();
                ImageLoaderManager.getInstance().showImage(pictureView, url, null);
                holder.setText(R.id.tv_name, item.getCookTitle());
                holder.setText(R.id.tv_desc, item.getCookDesc());
                holder.setText(R.id.tv_time, DateFormatUtils.formatDate(item.getCookPublishTime()));
            }
        };
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        getPresenter().getMyCollectionList(mPageNum, Config.PAGE_SIZE);
    }

    @Override
    protected void initData() {

    }

    /* Click */

    @CheckNet
    @Override
    public void onItemClick(View view, int position) {
        BaseCookbook cookbook = mDataList.get(position - 1);
        long userId = cookbook.getCookUserId();
        long cookId = cookbook.getCookId();
        PreviewCookbookActivity.startActivity(this, userId, cookId);
    }


    /* MVP View */

    @Override
    public void onGetCollectionList(DataList<BaseCookbook> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyItemInserted(lastPosition);
    }

    @Override
    public void onRefreshCollectionListSuccess(DataList<BaseCookbook> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyDataSetChanged();
        mRecyclerView.stopRefresh();
    }

    @Override
    public void onRefreshCollectionListFail() {
        mDataList.clear();
        mRecyclerView.stopRefresh();
    }

    private void addLoadView(DataList<BaseCookbook> data) {
        long total = data.getTotal();
        if (total > Config.PAGE_SIZE) {
            mRecyclerView.removeLoadViewCreator();
            mRecyclerView.setLoadViewCreator(LOAD_CREATOR);
            mRecyclerView.setOnLoadMoreListener(this);
        }
    }

    @Override
    public void onLoadCollectionListSuccess(DataList<BaseCookbook> data) {
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
        getPresenter().refreshCollectionList(mPageNum, Config.PAGE_SIZE);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadCollectionList(mPageNum, Config.PAGE_SIZE);
    }

}
