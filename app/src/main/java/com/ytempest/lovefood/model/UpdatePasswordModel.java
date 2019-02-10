package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.contract.UpdatePasswordContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.RetrofitClient;

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
