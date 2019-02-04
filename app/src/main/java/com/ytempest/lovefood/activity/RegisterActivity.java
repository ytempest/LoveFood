package com.ytempest.lovefood.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.RegisterContract;
import com.ytempest.lovefood.presenter.RegisterPresenter;

/**
 * @author ytempest
 * @date 2019/2/2
 */
@InjectPresenter(RegisterPresenter.class)
public class RegisterActivity extends BaseSkinActivity<RegisterContract.Presenter> implements RegisterContract.RegisterView, RegisterContract {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initTitle() {
        Toolbar tooBar = findViewById(R.id.toolbar);
        tooBar.setNavigationIcon(R.drawable.icon_left_arrow);

        setSupportActionBar(tooBar);

        //设置不现实自带的title文字
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
