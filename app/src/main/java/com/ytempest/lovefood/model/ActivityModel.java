package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.contract.ActivityContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class ActivityModel extends BaseModel implements ActivityContract.Model {
    @Override
    public Observable<BaseResult<DataList<ActivityInfo>>> getActivityList(int pageNum, int pageSize) {
        return RetrofitClient.client().getService().getActivityList(pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
