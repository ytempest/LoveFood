package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.AccountManageContract;
import com.ytempest.lovefood.mvp.contract.ReleaseCookbookContract;
import com.ytempest.lovefood.mvp.model.ReleaseCookbookModel;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(ReleaseCookbookModel.class)
public class ReleaseCookbookPresenter extends BasePresenter<ReleaseCookbookContract.ReleaseCookbookView, ReleaseCookbookContract.Model>
        implements ReleaseCookbookContract.Presenter {

}
