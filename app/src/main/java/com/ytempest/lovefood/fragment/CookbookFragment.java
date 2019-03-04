package com.ytempest.lovefood.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.baselibrary.view.CustomToast;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonRecyclerAdapter;
import com.ytempest.baselibrary.view.recyclerview.adapter.CommonViewHolder;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.CookbookContract;
import com.ytempest.lovefood.http.data.CookClassify;
import com.ytempest.lovefood.presenter.CookbookPresenter;
import com.ytempest.lovefood.widget.CookClassifyView;
import com.ytempest.lovefood.widget.ItemGroupView;

import butterknife.BindView;

/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(CookbookPresenter.class)
public class CookbookFragment extends BaseFragment<CookbookContract.Presenter> implements CookbookContract.CookbookView, CookbookContract,
        CookClassifyView.Callback {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cookbook;
    }

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new CommonRecyclerAdapter<CookClassify>(
                getContext(), CookClassify.getCookClassifyList(), R.layout.item_cook_group_text) {
            @Override
            protected void bindViewData(CommonViewHolder holder, CookClassify item) {
                CookClassifyView view = (CookClassifyView) holder.itemView;
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

    @Override
    public void onItemClick(int index, String classify, String type) {
        CustomToast.getInstance().show(index + "," + classify + ", " + type);
    }
}