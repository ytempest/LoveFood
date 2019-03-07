package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.UserInfo;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface PersonalContract extends IContract {
    interface Presenter extends IPresenter {
        UserInfo getUserInfo();
    }

    interface PersonalView extends IView {
    }

    interface Model extends IModel {
    }
}
