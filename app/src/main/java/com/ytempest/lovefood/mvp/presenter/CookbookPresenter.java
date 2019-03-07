package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.CookbookContract;
import com.ytempest.lovefood.mvp.model.CookbookModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(CookbookModel.class)
public class CookbookPresenter extends BasePresenter<CookbookContract.CookbookView, CookbookContract.Model> implements CookbookContract.Presenter {
}
