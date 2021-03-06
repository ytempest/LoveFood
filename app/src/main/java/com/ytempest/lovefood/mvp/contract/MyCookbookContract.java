package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public interface MyCookbookContract extends IContract {
    interface Presenter extends IPresenter {
        void getMyCookbookList(int pageNum, int pageSize);

        void refreshCookbookList(int pageNum, int pageSize);

        void loadCookbookList(int pageNum, int pageSize);
    }

    interface MyCookbookView extends IView {
        void onGetCookbookList(DataList<BaseCookbook> data);

        void onRefreshCookbookList(DataList<BaseCookbook> data);

        void onLoadCookbookList(DataList<BaseCookbook> data);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<BaseCookbook>>> getMyCookbookList(long userId, int pageNum, int pageSize);
    }
}
