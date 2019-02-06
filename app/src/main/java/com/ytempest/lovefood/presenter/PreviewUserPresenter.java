package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.MainContract;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.model.MainModel;
import com.ytempest.lovefood.model.PreviewUserModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(PreviewUserModel.class)
public class PreviewUserPresenter extends BasePresenter<PreviewUserContract.PreviewUserView, PreviewUserContract.Model>
        implements PreviewUserContract.Presenter {
}
