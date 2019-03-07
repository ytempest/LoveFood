package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface UpdateUserContract extends IContract {
    interface Presenter extends IPresenter {
        UserInfo getUserInfo();

        void updateUserInfo(Map<String, RequestBody> partMap);
    }

    interface UpdateUserView extends IView {
    }

    interface Model extends IModel {
        Observable<BaseResult<UserInfo>> updateUserInfo(Map<String, RequestBody> partMap);

        void saveUserInfo(UserInfo data);
    }
}
