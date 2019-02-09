package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public interface MyCookbookContract extends IContract {
    interface Presenter extends IPresenter {
    }

    interface MyCookbookView extends IView {
    }

    interface Model extends IModel {
    }
}
