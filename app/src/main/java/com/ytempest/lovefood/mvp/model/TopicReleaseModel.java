package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/3/31
 */
public class TopicReleaseModel extends BaseModel implements TopicReleaseContract.Model {

    @Override
    public Observable<BaseResult<Object>> releaseTopic(Map<String, RequestBody> partMap) {
        return RetrofitClient.client().getService().releaseTopic(partMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
