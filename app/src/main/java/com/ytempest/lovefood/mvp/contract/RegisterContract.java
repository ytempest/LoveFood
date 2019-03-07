package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/4
 */
public interface RegisterContract extends IContract {
    interface Presenter extends IPresenter {

        void register(String account, String password, String country, String phone, String verifyCode);

        /**
         * 获取短信验证码
         */
        void getVerificationCode(String country, String phone);
    }

    interface RegisterView extends IView {
        /**
         * 当成功发送给短信验证码时回调
         */
        void onSendCodeSuccess();

        /**
         * 验证过程的出现一些验证问题回调
         *
         * @param code {@link com.ytempest.lovefood.common.DefaultEventHandler}
         */
        void onVerifyCodeError(int code);

        /**
         * 验证时出现异常时回调
         */
        void onVerifyFail(Throwable t);
    }

    interface Model extends IModel {
        Observable<BaseResult<UserInfo>> registerUser(String account, String password, String phone);

        void saveUserInfo(UserInfo data);
    }
}
