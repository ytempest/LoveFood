package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.mvp.contract.PreviewCookbookContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/7
 */
public class PreviewCookbookModel extends BaseModel implements PreviewCookbookContract.Model {

    @Override
    public Observable<BaseResult<CookbookInfo>> getCookbookInfo(long cookId) {
        return RetrofitClient.client().getService().getCookbookInfo(cookId)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Boolean>> judgeUserCollection(long userId, long cookId) {
        return RetrofitClient.client().getService().isUserCollection(userId, cookId)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Boolean>> collectionCookbook(long userId, long cookId) {
        return RetrofitClient.client().getService().collectionCookbook(userId, cookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
