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
 * @date 2019/2/1
 */
public interface PreviewCookbookContract extends IContract {
    interface Presenter extends IPresenter {
        void getCookbookInfo(long cookId);
    }

    interface PreviewCookbookView extends IView {
        void onGetCookbookInfo(CookbookInfo data);
    }

    interface Model extends IModel {
        Observable<BaseResult<CookbookInfo>> getCookbookInfo(long cookId);
    }
}
