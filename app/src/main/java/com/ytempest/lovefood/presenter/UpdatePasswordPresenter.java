package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.UpdatePasswordContract;
import com.ytempest.lovefood.model.UpdatePasswordModel;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(UpdatePasswordModel.class)
public class UpdatePasswordPresenter extends BasePresenter<UpdatePasswordContract.UpdatePasswordView, UpdatePasswordContract.Model>
        implements UpdatePasswordContract.Presenter {

}
