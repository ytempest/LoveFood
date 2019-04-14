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
public interface MyCollectionContract extends IContract {
    interface Presenter extends IPresenter {
        void getMyCollectionList(int pageNum, int pageSize);

        void refreshCollectionList(int pageNum, int pageSize);

        void loadCollectionList(int pageNum, int pageSize);
    }

    interface MyCollectionView extends IView {
        void onGetCollectionList(DataList<BaseCookbook> data);

        void onRefreshCollectionListSuccess(DataList<BaseCookbook> data);

        void onRefreshCollectionListFail();

        void onLoadCollectionListSuccess(DataList<BaseCookbook> data);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<BaseCookbook>>> getMyCollectionList(long userId, int pageNum, int pageSize);
    }
}
