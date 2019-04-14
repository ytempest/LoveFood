package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.MyPartakeContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public class MyPartakeModel extends BaseModel implements MyPartakeContract.Model {
    @Override
    public Observable<BaseResult<DataList<BaseActivityInfo>>> getMyPartakeList(long userId, int pageNum, int pageSize) {
        return RetrofitClient.client().getService().getUserPartakeList(userId, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
