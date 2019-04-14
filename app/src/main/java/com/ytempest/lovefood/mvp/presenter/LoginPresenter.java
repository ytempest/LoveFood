package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.LoginContract;
import com.ytempest.lovefood.mvp.model.LoginModel;
import com.ytempest.lovefood.util.ResultUtils;

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
                .subscribe(new BaseObserver<BaseResult<UserInfo>>() {
                    @Override
                    public void onNext(BaseResult<UserInfo> value) {
                        if (value.getCode() == ResultUtils.SUCCESS) {
                            // 保存用户数据
                            getModel().saveUserInfo(value.getData());

                            // 通知view请求成功
                            getView().onRequestSuccess(value.getMsg());

                        } else if (value.getCode() == ResultUtils.ERROR) {
                            getView().onRequestFail(value.getMsg());
                        }
                    }
                });
    }
}
