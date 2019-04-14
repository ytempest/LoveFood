package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.mvp.contract.UpdatePasswordContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 *         Description：
 */
public class UpdatePasswordModel extends BaseModel implements UpdatePasswordContract.Model {

    @Override
    public Observable<BaseResult<Object>> updatePassword(Long userId, String oldPwd, String newPwd, String confirmPwd) {
        return RetrofitClient.client().getService().updatePassword(userId, oldPwd, newPwd, confirmPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
