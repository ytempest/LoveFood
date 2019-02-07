package com.ytempest.lovefood.contract;

import android.widget.ImageView;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/6
 */
public interface PreviewUserContract extends IContract {

    interface Presenter extends IPresenter {
        void requestUserInfo(long userId);

        void showImage(String suffixUrl, ImageView view);
    }

    interface PreviewUserView extends IView {
        void onRequestUserInfo(UserInfo data);
    }

    interface Model extends IModel {
        Observable<BaseResult<UserInfo>> getUserInfo(long userId);
    }

}

