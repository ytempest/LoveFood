package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.RegisterContract;
import com.ytempest.lovefood.model.RegisterModel;

/**
 * @author ytempest
 *         Description：
 */
@InjectModel(RegisterModel.class)
public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView, RegisterContract.Model>
        implements RegisterContract.Presenter {
    @Override
    public void register(String account, String password, String phone) {

    }
}
