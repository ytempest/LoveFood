package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.framelibrary.encrypt.EncryptUtils;
import com.ytempest.lovefood.contract.LoginContract;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.http.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public Observable<BaseResult> getLoginData(String account, String password) {
        // 加密密码
        password = EncryptUtils.encrypt(password);
        return RetrofitClient.client().getService().login(account, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
