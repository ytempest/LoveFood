package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.LoginContract;
import com.ytempest.lovefood.model.LoginModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(LoginModel.class)
public class LoginPresenter extends BasePresenter<LoginContract.LoginView, LoginContract.Model> implements LoginContract.Presenter {
}
