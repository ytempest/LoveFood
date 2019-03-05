package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface ActivityContract extends IContract {
    interface Presenter extends IPresenter {
        void loadActivityList(int pageNum, int pageSize);
    }

    interface ActivityView extends IView {
    }

    interface Model extends IModel {
    }
}
