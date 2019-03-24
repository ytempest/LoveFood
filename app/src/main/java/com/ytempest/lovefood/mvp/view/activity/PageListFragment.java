package com.ytempest.lovefood.mvp.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.MvpFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.RefreshRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.PageListContract;
import com.ytempest.lovefood.mvp.presenter.PageListPresenter;
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
        RefreshRecyclerView.OnRefreshMoreListener {

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private int mPageNum = 1;
    private long mActId;
    private ArrayList<BaseCookbook> mDataList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_page_list;
    }

    @Override
    protected void initView() {
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new CommonRecyclerAdapter<BaseCookbook>(
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
        });
    }

    @Override
    protected void initData() {

    }

    public void setData(long actId) {
        this.mActId = actId;
        mRecyclerView.startRefresh();
    }

    /* Refresh */

    @Override
    public void onRefresh() {
        mPageNum = 1;
        getPresenter().refreshPartakeCookList(mActId, mPageNum, Config.PAGE_SIZE);
    }

    /* View MVP */

    @Override
    public void onGetPartakeCookListSuccess(DataList<BaseCookbook> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());
    }

    @Override
    public void onGetPartakeCookListFail(String msg) {
        mRecyclerView.stopRefresh();
        CustomToast.getInstance().show(msg);
    }
}
