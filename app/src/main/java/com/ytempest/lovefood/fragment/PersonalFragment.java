package com.ytempest.lovefood.fragment;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.baselibrary.base.mvp.inject.InjectPresenter;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.contract.PersonalContract;
import com.ytempest.lovefood.presenter.PersonalPresenter;


/**
 * @author ytempest
 *         Description：
 */
@InjectPresenter(PersonalPresenter.class)
public class PersonalFragment extends BaseFragment<PersonalContract.Presenter> implements PersonalContract.PersonalView, PersonalContract {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }
}