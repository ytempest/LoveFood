package com.ytempest.lovefood.activity;

import android.content.Context;
import android.content.Intent;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.framelibrary.view.NavigationView;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.EditCookbookContract;
import com.ytempest.lovefood.presenter.EditCookbookPresenter;

import butterknife.BindView;

/**
 * @author ytempest
 * @date 2019/2/16
 */
@InjectPresenter(EditCookbookPresenter.class)
public class EditCookbookActivity extends BaseSkinActivity<EditCookbookContract.Presenter>
        implements EditCookbookContract.EditCookbookView, EditCookbookContract {

    private static final String COOK_ID = "cook_id";

    public static void startActivity(Context context, long cookId) {
        Intent intent = new Intent(context, EditCookbookActivity.class);
        intent.putExtra(COOK_ID, cookId);
        context.startActivity(intent);
    }

    @BindView(R.id.navigation_view)
    protected NavigationView mNavigationView;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_edit_cookbook;
    }

    @Override
    protected void initTitle() {
        mNavigationView.enableLeftFinish(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        long cookId = getIntent().getLongExtra(COOK_ID, -1);
        if (cookId != -1) {

        }
    }
}