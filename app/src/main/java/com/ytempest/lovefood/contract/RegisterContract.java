package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;

/**
 * @author ytempest
 * @date 2019/2/4
 */
public interface RegisterContract extends IContract {
    interface Presenter extends IPresenter {
        void register(String account, String password, String phone);
    }

    interface RegisterView extends IView {
    }

    interface Model extends IModel {
    }
}
