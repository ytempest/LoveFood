package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.mvp.contract.EditCookbookContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/16
 */
public class EditCookbookModel extends BaseModel implements EditCookbookContract.Model {

    @Override
    public Observable<BaseResult<CookbookInfo>> getCookbookInfo(long cookId) {
        return RetrofitClient.client().getService().getCookbookInfo(cookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseResult<CookbookInfo>> saveCookbookInfo(Map<String, RequestBody> map) {
        return RetrofitClient.client().getService().updateCookInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
