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
 * @date 2019/3/22
 */
public interface PageListContract extends IContract {
    interface Presenter extends IPresenter {
        void refreshPartakeCookList(long actId, int pageNum, int pageSize);
    }

    interface PageListView extends IView {
        void onGetPartakeCookListSuccess(DataList<BaseCookbook> data);

        void onGetPartakeCookListFail(String msg);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<BaseCookbook>>> getPartakeCookList(long actId, int pageNum, int pageSize);
    }
}
