package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.MyCollectionContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public class MyCollectionModel extends BaseModel implements MyCollectionContract.Model {
    @Override
    public Observable<BaseResult<DataList<BaseCookbook>>> getMyCollectionList(long userId, int pageNum, int pageSize) {
        return RetrofitClient.client().getService().getUserCollectionList(userId, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
