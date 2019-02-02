package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.data.BaseResult;

import io.reactivex.Observable;


/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface LoginContract extends IContract {
    interface Presenter extends IPresenter {
        void login(String account, String password);
    }

    interface LoginView extends IView {

    }

    interface Model extends IModel {
        Observable<BaseResult> getLoginData(String account, String password);
    }
}
