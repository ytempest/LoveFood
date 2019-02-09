package com.ytempest.lovefood.activity.personal;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.imageloader.ImageLoaderManager;
import com.ytempest.baselibrary.view.recyclerview.LoadRecyclerView;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.adapter.DefaultRefreshViewCreator;
import com.ytempest.lovefood.contract.MyCookbookContract;
import com.ytempest.lovefood.data.BaseCookbook;
import com.ytempest.lovefood.data.DataList;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.presenter.MyCookbookPresenter;
import com.ytempest.lovefood.util.Config;
import com.ytempest.lovefood.util.DateUtils;

import java.util.List;

import butterknife.BindView;

@InjectPresenter(MyCookbookPresenter.class)
public class MyCookbookActivity extends BaseSkinActivity<MyCookbookContract.Presenter>
        implements MyCookbookContract.MyCookbookView, MyCookbookContract,
        CommonRecyclerAdapter.OnItemClickListener {

    private static final String TAG = "MyCookbookActivity";

    private int mPageNum = 1;

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected LoadRecyclerView mRecyclerView;

    private List<BaseCookbook> mDataList;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
        mNavigationView.setTitleText("我的菜谱");
    }

    @Override
    protected void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyCookbookActivity.this));
        mRecyclerView.addRefreshViewCreator(new DefaultRefreshViewCreator());

        getPresenter().getMyCookbookList(mPageNum, Config.PAGE_SIZE);
    }

    @Override
    protected void initData() {

    }

    /* MVP View */

    @Override
    public void onGetCookbookList(DataList<BaseCookbook> data) {
        mDataList = data.getList();
        CommonRecyclerAdapter<BaseCookbook> adapter = new CommonRecyclerAdapter<BaseCookbook>(
                MyCookbookActivity.this, data.getList(), R.layout.item_cook_book) {
            @Override
            protected void bindViewData(CommonViewHolder holder, BaseCookbook item) {
                ImageView pictureView = holder.getView(R.id.iv_picture);
                String url = RetrofitClient.client().getUrl() + item.getCookImageUrl();
                ImageLoaderManager.getInstance().showImage(pictureView, url, null);
                holder.setText(R.id.tv_name, item.getCookTitle());
                holder.setText(R.id.tv_desc, item.getCookDesc());
                holder.setText(R.id.tv_time, DateUtils.format(item.getCookPublishTime()));


            }
        };
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        int cookId = mDataList.get(position-1).getCookId();

        showToast("cookId = " + cookId);
//        Intent intent = new Intent(MyCookbookActivity.this, MyCookbookActivity.class);
//        intent.putExtra(, cookId);
//        startActivity(intent);
    }
}
