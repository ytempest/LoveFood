package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.ActivityContract;
import com.ytempest.lovefood.contract.PreviewCookbookContract;
import com.ytempest.lovefood.model.ActivityModel;
import com.ytempest.lovefood.model.PreviewCookbookModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(PreviewCookbookModel.class)
public class PreviewCookbookPresenter extends BasePresenter<PreviewCookbookContract.PreviewCookbookView,
        PreviewCookbookContract.Model> implements ActivityContract.Presenter {
}
