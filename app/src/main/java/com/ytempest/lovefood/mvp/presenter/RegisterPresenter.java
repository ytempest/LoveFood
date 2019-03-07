package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.common.DefaultEventHandler;
import com.ytempest.lovefood.mvp.contract.RegisterContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.model.RegisterModel;
import com.ytempest.lovefood.util.ResultUtils;

import java.util.HashMap;
import java.util.Map;

import cn.smssdk.SMSSDK;

/**
 * @author ytempest
 *         Description：
 */
@InjectModel(RegisterModel.class)
public class RegisterPresenter extends BasePresenter<RegisterContract.RegisterView, RegisterContract.Model>
        implements RegisterContract.Presenter {

    private final Map<String, String> DATA_MAP = new HashMap<>();
    private DefaultEventHandler mEventHandler;


    public RegisterPresenter() {
        mEventHandler = new DefaultEventHandler();
        mEventHandler.setListener(new DefaultEventHandler.OnVerifyListener() {
            @Override
            public void onSendCodeSuccess() {
                getView().onSendCodeSuccess();
            }

            @Override
            public void onVerifyCodeSuccess(String country, String verifyPhone) {
                String account = DATA_MAP.get("account");
                String password = DATA_MAP.get("password");
                String phone = DATA_MAP.get("phone");
                getModel().registerUser(account, password, phone)
                        .subscribe(new BaseObserver<BaseResult<UserInfo>>() {
                            @Override
                            public void onNext(BaseResult<UserInfo> result) {
                                super.onNext(result);
                                int code = result.getCode();
                                if (code == ResultUtils.SUCCESS) {
                                    getModel().saveUserInfo(result.getData());
                                    getView().onRequestSuccess(result.getMsg());

                                } else if (code == ResultUtils.ERROR) {
                                    getView().onRequestFail(result.getMsg());
                                }
                            }
                        });
            }

            @Override
            public void onVerifyCodeError(int code) {
                getView().onVerifyCodeError(code);
            }

            @Override
            public void onVerifyFail(Throwable t) {
                getView().onVerifyFail(t);
            }
        });
        SMSSDK.registerEventHandler(mEventHandler);
    }


    @Override
    public void register(String account, String password, String country, String phone, String verifyCode) {
        getView().onRequestStart("正在注册...");
        DATA_MAP.put("account", account);
        DATA_MAP.put("password", password);
        DATA_MAP.put("phone", phone);
        SMSSDK.submitVerificationCode(country, phone, verifyCode);
    }


    @Override
    public void getVerificationCode(String country, String phone) {
        SMSSDK.getVerificationCode(country, phone);
    }

    @Override
    public void detach() {
        SMSSDK.unregisterEventHandler(mEventHandler);
        super.detach();
    }
}
