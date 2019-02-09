package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.data.BaseResult;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public interface UpdatePasswordContract extends IContract {
    interface Presenter extends IPresenter {
        void updatePassword(String oldPwd, String newPwd, String confirmPwd);
    }

    interface UpdatePasswordView extends IView {
    }

    interface Model extends IModel {
        Observable<BaseResult<Object>> updatePassword(Long userId, String oldPwd, String newPwd, String confirmPwd);
    }
}
