package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.PageListContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/3/22
 */
public class PageListModel extends BaseModel implements PageListContract.Model {

    @Override
    public Observable<BaseResult<DataList<BaseCookbook>>> getPartakeCookList(long actId, int pageNum, int pageSize) {
        return RetrofitClient.client().getService().getPartakeCookList(actId, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
