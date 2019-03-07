package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.MainContract;
import com.ytempest.lovefood.mvp.model.MainModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(MainModel.class)
public class MainPresenter extends BasePresenter<MainContract.MainView, MainContract.Model> implements MainContract.Presenter {
}
