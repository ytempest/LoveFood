package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface CookbookContract extends IContract {
    interface Presenter extends IPresenter {
    }

    interface CookbookView extends IView {
    }

    interface Model extends IModel {
    }
}
