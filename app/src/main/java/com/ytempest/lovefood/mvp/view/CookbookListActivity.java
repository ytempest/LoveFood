package com.ytempest.lovefood.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.ytempest.lovefood.callback.WrapCallback;
import com.ytempest.lovefood.common.adapter.DefaultLoadViewCreator;
import com.ytempest.lovefood.common.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.CookbookListContract;
import com.ytempest.lovefood.mvp.presenter.CookbookListPresenter;
import com.ytempest.lovefood.util.CommonUtils;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;
import com.ytempest.lovefood.widget.ListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(CookbookListPresenter.class)
public class CookbookListActivity extends BaseSkinActivity<CookbookListContract.Presenter>
        implements CookbookListContract.CookbookListView, CookbookListContract,
        CommonRecyclerAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnLongClickListener {

    private static final String TAG = "CookbookListActivity";

    private static final String KEY_GROUP = "key_group";
    private static final String KEY_TYPE = "key_type";

    public static void startActivity(Context context, String group, String type) {
        Intent intent = new Intent(context, CookbookListActivity.class);
        intent.putExtra(KEY_GROUP, group);
        intent.putExtra(KEY_TYPE, type);
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

    private String mGroup;
    private String mType;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initTitle() {
        mGroup = getIntent().getStringExtra(KEY_GROUP);
        mType = getIntent().getStringExtra(KEY_TYPE);

        mNavigationView.enableLeftFinish(this);
        mNavigationView.setTitleText(mType);
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CookbookListActivity.this));
        mAdapter = new CommonRecyclerAdapter<BaseCookbook>(
                CookbookListActivity.this, mDataList, R.layout.item_cook_book) {
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
        mAdapter.setOnItemLongClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void initData() {
        getPresenter().getCookbookList(mPageNum, Config.PAGE_SIZE, mGroup, mType);
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

    @Override
    public boolean onItemLongClick(View view, int position) {
        ListDialog dialog = getListDialog();
        dialog.addData(ListDialog.SINGLE, mDataList.get(position - 1));
        dialog.show();
        return true;
    }

    private ListDialog mListDialog;

    private ListDialog getListDialog() {
        if (mListDialog == null) {
            List<Pair<Integer, String>> list = new ArrayList<>();
            list.add(new Pair<>(ListDialog.SINGLE, "编辑菜谱"));
            mListDialog = new ListDialog(getContext(), list, new WrapCallback<Integer, Object>() {
                @Override
                public void onCall(Integer firstParam, Object secondParam) {
                    BaseCookbook cookbook = (BaseCookbook) secondParam;
                    EditCookbookActivity.startActivity(CookbookListActivity.this, cookbook.getCookId());
                    mListDialog.dismiss();
                }
            });
        }
        return mListDialog;
    }

    /* MVP View */

    @Override
    public void onGetCookbookList(DataList<BaseCookbook> data) {
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);

        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyItemInserted(lastPosition);
    }

    @Override
    public void onFailGetCookbookList(String msg) {
        TextView tipText = CommonUtils.getTipText(getContext(), mRecyclerView);
        tipText.setText(msg);
        mRecyclerView.addHeaderView(tipText);
    }

    @Override
    public void onRefreshCookbookList(DataList<BaseCookbook> data) {
        mDataList.clear();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyDataSetChanged();
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
    public void onLoadCookbookList(DataList<BaseCookbook> data) {
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
        getPresenter().refreshCookbookList(mPageNum, Config.PAGE_SIZE, mGroup, mType);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadCookbookList(mPageNum, Config.PAGE_SIZE, mGroup, mType);
    }

}
