package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.ActivityDetailInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.mvp.contract.ActivityDetailContract;
import com.ytempest.lovefood.util.UserHelper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/3/21
 */
public class ActivityDetailModel extends BaseModel implements ActivityDetailContract.Model {

    @Override
    public Observable<BaseResult<ActivityDetailInfo>> getActivityDetail(long actId) {
        return RetrofitClient.client().getService().getActivityDetail(actId)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Boolean>> isUserPartakeActivity(long actId) {
        Long userId = UserHelper.getInstance().getUserInfo().getUserId();
        return RetrofitClient.client().getService().isPartakeActivity(userId, actId)
                .subscribeOn(Schedulers.io());
    }
}
