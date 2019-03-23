package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.ActivityDetailInfo;
import com.ytempest.lovefood.http.data.BaseResult;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/3/21
 */
public interface ActivityDetailContract extends IContract {

    interface Presenter extends IPresenter {
        void getActivityDetail(long actId);
    }

    interface ActivityDetailView extends IView {
        void onGetActivityDetailSuccess(ActivityDetailInfo data, boolean isUserPartakeActivity);
    }

    interface Model extends IModel {
        Observable<BaseResult<ActivityDetailInfo>> getActivityDetail(long actId);

        Observable<BaseResult<Boolean>> isUserPartakeActivity(long actId);
    }

}

