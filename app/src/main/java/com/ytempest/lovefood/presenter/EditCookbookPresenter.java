package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.EditCookbookContract;
import com.ytempest.lovefood.model.EditCookbookModel;
import com.ytempest.lovefood.model.PreviewCookbookModel;

/**
 * @author ytempest
 * @date 2019/2/16
 */
@InjectModel(EditCookbookModel.class)
public class EditCookbookPresenter extends BasePresenter<EditCookbookContract.EditCookbookView,
        EditCookbookContract.Model> implements EditCookbookContract.Presenter {

}
