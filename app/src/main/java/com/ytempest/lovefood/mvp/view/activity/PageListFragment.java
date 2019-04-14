package com.ytempest.lovefood.mvp.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.MvpFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.LoadViewCreator;
import com.ytempest.baselibrary.view.recyclerview.RefreshRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.PageListContract;
import com.ytempest.lovefood.mvp.presenter.PageListPresenter;
import com.ytempest.lovefood.mvp.view.PreviewCookbookActivity;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author ytempest
 * @date 2019/3/22
 */
@InjectPresenter(PageListPresenter.class)
public class PageListFragment extends MvpFragment<PageListContract.Presenter>
        implements PageListContract.PageListView,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private int mPageNum = 1;
    private Long mActId;
    private final ArrayList<BaseCookbook> mDataList = new ArrayList<>();
    private static LoadViewCreator LOAD_CREATOR = new DefaultLoadViewCreator();
    private CommonRecyclerAdapter<BaseCookbook> mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_page_list;
    }

    @Override
    protected void initView() {
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CommonRecyclerAdapter<BaseCookbook>(
                getContext(), mDataList, R.layout.item_cook_book) {
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
    }

    @Override
    protected void initData() {

    }

    public void setData(long actId) {
        if (mActId == null) {
            this.mActId = actId;
            mRecyclerView.startRefresh();
        }
    }

    /* Refresh Load */

    @Override
    public void onRefresh() {
        mPageNum = 1;
        getPresenter().refreshPartakeCookList(mActId, mPageNum, Config.PAGE_SIZE);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadPartakeCookList(mActId, mPageNum, Config.PAGE_SIZE);
    }

    /* View MVP */

    @Override
    public void onRefreshPartakeCookListSuccess(DataList<BaseCookbook> data) {
        mRecyclerView.stopRefresh();
        mDataList.clear();
        mDataList.addAll(data.getList());

        addLoadView(data);

        mAdapter.notifyDataSetChanged();
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
    public void onGetPartakeCookListFail(String msg) {
        mRecyclerView.stopRefresh();
        CustomToast.getInstance().show(msg);
    }

    @Override
    public void onLoadPartakeCookList(DataList<BaseCookbook> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());
        mAdapter.notifyItemInserted(lastPosition + 1);
        mRecyclerView.stopLoad();
        if (mPageNum == data.getPageCount()) {
            mRecyclerView.removeLoadViewCreator();
        }
    }

    /* Click */

    @Override
    public void onItemClick(View view, int position) {
        BaseCookbook cookbook = mDataList.get(position - 1);
        long userId = cookbook.getCookUserId();
        long cookId = cookbook.getCookId();
        PreviewCookbookActivity.startActivity(getContext(), userId, cookId);
    }
}
