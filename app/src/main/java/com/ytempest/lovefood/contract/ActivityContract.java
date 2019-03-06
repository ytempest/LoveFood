package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface ActivityContract extends IContract {
    interface Presenter extends IPresenter {
        void getActivityList(int pageNum, int pageSize);

        void refreshActivityList(int pageNum, int pageSize);

        void loadActivityList(int pageNum, int pageSize);
    }

    interface ActivityView extends IView {
        void onGetActivityList(DataList<ActivityInfo> data);

        void onRefreshActivityList(DataList<ActivityInfo> data);

        void onLoadActivityList(DataList<ActivityInfo> data);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<ActivityInfo>>> getActivityList(int pageNum, int pageSize);
    }
}
