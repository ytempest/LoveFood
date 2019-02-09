package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.CookbookContract;
import com.ytempest.lovefood.contract.MyCookbookContract;
import com.ytempest.lovefood.model.MyCookbookModel;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(MyCookbookModel.class)
public class MyCookbookPresenter extends BasePresenter<MyCookbookContract.MyCookbookView, MyCookbookContract.Model>
        implements MyCookbookContract.Presenter {
}
