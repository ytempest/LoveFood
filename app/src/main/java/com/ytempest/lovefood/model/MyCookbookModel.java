package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.contract.MyCookbookContract;
import com.ytempest.lovefood.data.BaseCookbook;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.DataList;
import com.ytempest.lovefood.http.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/9
 */
public class MyCookbookModel extends BaseModel implements MyCookbookContract.Model {
    @Override
    public Observable<BaseResult<DataList<BaseCookbook>>> getMyCookbookList(long userId, int pageNum, int pageSize) {
        return RetrofitClient.client().getService().getUserCookList(userId, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
