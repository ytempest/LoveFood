package com.ytempest.lovefood.activity;

import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.framelibrary.base.BaseSkinActivity;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.UpdatePasswordContract;
import com.ytempest.lovefood.presenter.UpdatePasswordPresenter;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectPresenter(UpdatePasswordPresenter.class)
public class UpdatePasswordActivity extends BaseSkinActivity<UpdatePasswordContract.Presenter>
        implements UpdatePasswordContract.UpdatePasswordView, UpdatePasswordContract {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
