package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.util.UserHelper;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class UpdateUserModel extends BaseModel implements UpdateUserContract.Model {

    @Override
    public Observable<BaseResult<UserInfo>> updateUserInfo(MultipartBody.Part headPart, Map<String, RequestBody> partMap) {
        return RetrofitClient.client().getService().updateUserInfo(headPart, partMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void saveUserInfo(UserInfo data) {
        UserHelper.getInstance().saveUserInfo(data);
    }
}
