package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/6
 */
public interface PreviewUserContract extends IContract {

    interface Presenter extends IPresenter {
        void requestUserInfo(long userId);
    }

    interface PreviewUserView extends IView {
        void onRequestUserInfo(UserInfo data);
    }

    interface Model extends IModel {
        Observable<BaseResult<UserInfo>> getUserInfo(long userId);
    }

}

