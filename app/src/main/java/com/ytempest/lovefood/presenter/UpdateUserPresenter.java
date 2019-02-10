package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.UpdateUserModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(UpdateUserModel.class)
public class UpdateUserPresenter extends BasePresenter<UpdateUserContract.UpdateUserView, UpdateUserContract.Model>
        implements UpdateUserContract.Presenter {

    @Override
    public UserInfo getUserInfo() {
        return UserHelper.getInstance().getUserInfo();
    }

    @Override
    public void updateUserInfo(MultipartBody.Part headPart, Map<String, RequestBody> partMap) {
        getView().onRequestStart("正在修改...");

        getModel().updateUserInfo(headPart, partMap)
                .subscribe(new BaseObserver<BaseResult<UserInfo>>() {
                    @Override
                    public void onNext(BaseResult<UserInfo> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            // 保存修改后的用户信息
                            getModel().saveUserInfo(result.getData());

                            // 传递修改后的用户信息给PersonalFragment
                            EventBus.getDefault().post(result.getData());

                            getView().onRequestSuccess(result.getMsg());
                        } else {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
