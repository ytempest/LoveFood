package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.lovefood.contract.LoginContract;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.LoginModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(LoginModel.class)
public class LoginPresenter extends BasePresenter<LoginContract.LoginView, LoginContract.Model> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    @Override
    public void login(String account, String password) {
        getView().onRequestStart("正在登录...");
        getModel().getLoginData(account, password)
                .subscribe(new BaseObserver<BaseResult>() {
                    @Override
                    public void onNext(BaseResult value) {
                        LogUtils.e(TAG, "onNext: " + Thread.currentThread());
                        getView().onRequestSuccess("登录成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, "onNext: " + Thread.currentThread());
                    }
                });
    }
}
