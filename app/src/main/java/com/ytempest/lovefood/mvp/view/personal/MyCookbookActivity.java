package com.ytempest.lovefood.mvp.view.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.MyCookbookContract;
import com.ytempest.lovefood.mvp.presenter.MyCookbookPresenter;
import com.ytempest.lovefood.mvp.view.EditCookbookActivity;
import com.ytempest.lovefood.mvp.view.PreviewCookbookActivity;
import com.ytempest.lovefood.mvp.view.cookbook.ReleaseCookbookActivity;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateFormatUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@InjectPresenter(MyCookbookPresenter.class)
public class MyCookbookActivity extends BaseSkinActivity<MyCookbookContract.Presenter>
        implements MyCookbookContract.MyCookbookView, MyCookbookContract,
        CommonRecyclerAdapter.OnItemClickListener,
        RefreshRecyclerView.OnRefreshMoreListener, LoadRecyclerView.OnLoadMoreListener, CommonRecyclerAdapter.OnLongClickListener {

    private static final String TAG = "MyCookbookActivity";

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
        mNavigationView.setTitleText("我的菜谱");
        mNavigationView.setRightText("添加");
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReleaseCookbookActivity.startActivity(getContext());
            }
        });
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyCookbookActivity.this));
        mRecyclerView.setRefreshViewCreator(new DefaultRefreshViewCreator());
        mRecyclerView.setOnRefreshMoreListener(this);
        mAdapter = new CommonRecyclerAdapter<BaseCookbook>(
                MyCookbookActivity.this, mDataList, R.layout.item_cook_book) {
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

        getPresenter().getMyCookbookList(mPageNum, Config.PAGE_SIZE);
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
            EditCookbookActivity.startActivity(MyCookbookActivity.this, cookbook.getCookId());
            mDialog.dismiss();
        }
    };

    private AlertDialog mDialog;

    private AlertDialog getDialog() {
        if (mDialog == null) {
            mDialog = new AlertDialog.Builder(MyCookbookActivity.this)
                    .setContentView(R.layout.dialog_edit_cookbook)
                    .addDefaultAnimation()
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setCanceledOnTouchOutside(true)
                    .create();
        }
        return mDialog;
    }

    /**
     * 当编辑过菜谱后更新列表
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEditCookbookInfoSuccess(CookbookInfo newCookbook) {
        View edit = mDialog.getView(R.id.tv_edit_cookbook);
        BaseCookbook cookbook = (BaseCookbook) edit.getTag();
        cookbook.setCookTitle(newCookbook.getCookTitle());
        cookbook.setCookDesc(newCookbook.getCookDesc());
        cookbook.setCookGroup(newCookbook.getCookGroup());
        cookbook.setCookType(newCookbook.getCookType());
        cookbook.setCookImageUrl(newCookbook.getCookImageUrl());
        cookbook.setCookPublishTime(newCookbook.getCookPublishTime());
        mAdapter.notifyDataSetChanged();
    }


    /* MVP View */

    @Override
    public void onGetCookbookList(DataList<BaseCookbook> data) {
        int lastPosition = mDataList.size();
        mDataList.addAll(data.getList());

        // 添加上拉刷新的View
        addLoadView(data);

        mAdapter.notifyItemInserted(lastPosition);
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
        getPresenter().refreshCookbookList(mPageNum, Config.PAGE_SIZE);
    }

    @Override
    public void onLoad() {
        mPageNum++;
        getPresenter().loadCookbookList(mPageNum, Config.PAGE_SIZE);
    }

}
