package com.ytempest.lovefood.mvp.view.cookbook;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.mvp.view.CookbookListActivity;
import com.ytempest.lovefood.aop.CheckNet;
import com.ytempest.lovefood.mvp.contract.CookbookContract;
import com.ytempest.lovefood.http.data.CookClassify;
import com.ytempest.lovefood.mvp.presenter.CookbookPresenter;
import com.ytempest.lovefood.widget.CookGroupView;

import butterknife.BindView;

/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(CookbookPresenter.class)
public class CookbookFragment extends BaseFragment<CookbookContract.Presenter> implements CookbookContract.CookbookView, CookbookContract,
        CookGroupView.Callback {

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cookbook;
    }

    @Override
    protected void initView() {
        mNavigationView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomToast.getInstance().show("发菜谱");
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CommonRecyclerAdapter<CookClassify>(
                getContext(), CookClassify.getCookClassifyList(), R.layout.item_cook_group) {
            @Override
            protected void bindViewData(CommonViewHolder holder, CookClassify item) {
                CookGroupView view = (CookGroupView) holder.itemView;
                view.setTitle(item.getGroup());
                view.setItem(item.getTypeList());
                view.setCallback(CookbookFragment.this);
            }
        });
    }

    @Override
    protected void initData() {
    }

    /* Click */

    @CheckNet
    @Override
    public void onItemClick(int index, String group, String type) {
        CookbookListActivity.startActivity(getContext(), group, type);
    }
}