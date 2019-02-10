package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.framelibrary.encrypt.EncryptUtils;
import com.ytempest.lovefood.contract.RegisterContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.util.UserHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 *         Description：
 */
public class RegisterModel extends BaseModel implements RegisterContract.Model {
    @Override
    public Observable<BaseResult<UserInfo>> registerUser(String account, String password, String phone) {
        password = EncryptUtils.encrypt(password);
        return RetrofitClient.client().getService().register(account, password, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveUserInfo(UserInfo data) {
        UserHelper.getInstance().saveUserInfo(data);
    }
}
