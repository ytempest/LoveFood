package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.CookbookContract;
import com.ytempest.lovefood.model.CookbookModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(CookbookModel.class)
public class CookbookPresenter extends BasePresenter<CookbookContract.View, CookbookContract.Model> implements CookbookContract.Presenter {
}
