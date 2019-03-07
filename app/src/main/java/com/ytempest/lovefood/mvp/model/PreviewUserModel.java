package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.mvp.contract.PreviewUserContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class PreviewUserModel extends BaseModel implements PreviewUserContract.Model {
    @Override
    public Observable<BaseResult<UserInfo>> getUserInfo(long userId) {
        return RetrofitClient.client().getService().getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
