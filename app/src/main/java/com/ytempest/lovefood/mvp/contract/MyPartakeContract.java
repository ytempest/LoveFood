package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public interface MyPartakeContract extends IContract {
    interface Presenter extends IPresenter {
        void getPartakeList(int pageNum, int pageSize);

        void refreshPartakeList(int pageNum, int pageSize);

        void loadPartakeList(int pageNum, int pageSize);
    }

    interface MyPartakeView extends IView {
        void onGetPartakeList(DataList<BaseActivityInfo> data);

        void onRefreshPartakeListSuccess(DataList<BaseActivityInfo> data);

        void onRefreshPartakeListFail();

        void onLoadPartakeListSuccess(DataList<BaseActivityInfo> data);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<BaseActivityInfo>>> getMyPartakeList(long userId, int pageNum, int pageSize);
    }
}
