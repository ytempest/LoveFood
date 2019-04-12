package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.mvp.contract.ReleaseCookbookContract;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/4/10
 */
public class ReleaseCookbookModel extends BaseModel implements ReleaseCookbookContract.Model {


    @Override
    public Observable<BaseResult<Object>> releaseCookbook(Map<String, RequestBody> map) {
        return RetrofitClient.client().getService().releaseCookbook(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
