package com.ytempest.lovefood.fragment;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.CookbookContract;
import com.ytempest.lovefood.presenter.CookbookPresenter;
import com.ytempest.lovefood.widget.ItemGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(CookbookPresenter.class)
public class CookbookFragment extends BaseFragment<CookbookContract.Presenter> implements CookbookContract.CookbookView, CookbookContract {

    @BindView(R.id.item_group)
    protected ItemGroupView mItemGroupView;

    @Override
    protected int getLayoutId() {
        return R.layout.item_cook_group;
    }

    @Override
    protected void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("list" + i);
        }
        mItemGroupView.setItem(list);
    }

    @Override
    protected void initData() {
    }
}