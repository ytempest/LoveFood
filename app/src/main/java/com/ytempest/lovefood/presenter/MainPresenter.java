package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.MainContract;
import com.ytempest.lovefood.model.MainModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(MainModel.class)
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {
}
