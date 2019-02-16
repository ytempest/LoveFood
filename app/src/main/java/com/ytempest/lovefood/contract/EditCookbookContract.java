package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/16
 */
public interface EditCookbookContract extends IContract {
    interface Presenter extends IPresenter {

    }

    interface EditCookbookView extends IView {

    }

    interface Model extends IModel {
        Observable<BaseResult<CookbookInfo>> getCookbookInfo(long cookId);
    }
}
