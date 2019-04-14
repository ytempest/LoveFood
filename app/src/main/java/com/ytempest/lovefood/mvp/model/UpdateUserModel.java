package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.mvp.contract.UpdateUserContract;
import com.ytempest.lovefood.util.UserHelper;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class UpdateUserModel extends BaseModel implements UpdateUserContract.Model {

    @Override
    public Observable<BaseResult<UserInfo>> updateUserInfo(Map<String, RequestBody> partMap) {
        return RetrofitClient.client().getService().updateUserInfo(partMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveUserInfo(UserInfo data) {
        UserHelper.getInstance().saveUserInfo(data);
    }
}
