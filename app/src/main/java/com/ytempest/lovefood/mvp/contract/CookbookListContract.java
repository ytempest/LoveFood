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
 * @date 2019/3/4
 */
public interface CookbookListContract extends IContract {
    interface Presenter extends IPresenter {
        void getCookbookList(int pageNum, int pageSize, String group, String type);

        void refreshCookbookList(int pageNum, int pageSize, String group, String type);

        void loadCookbookList(int pageNum, int pageSize, String group, String type);
    }

    interface CookbookListView extends IView {

        void onGetCookbookList(DataList<BaseCookbook> data);

        void onRefreshCookbookList(DataList<BaseCookbook> data);

        void onLoadCookbookList(DataList<BaseCookbook> data);

        void onFailGetCookbookList(String msg);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<BaseCookbook>>> getCookbookList(int pageNum, int pageSize, String group, String type);
    }
}
