package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.framelibrary.encrypt.EncryptUtils;
import com.ytempest.lovefood.contract.UpdatePasswordContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.UpdatePasswordModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(UpdatePasswordModel.class)
public class UpdatePasswordPresenter extends BasePresenter<UpdatePasswordContract.UpdatePasswordView, UpdatePasswordContract.Model>
        implements UpdatePasswordContract.Presenter {

    @Override
    public void updatePassword(String oldPwd, String newPwd, String confirmPwd) {
        getView().onRequestStart("正在修改..");

        // 密码加密
        oldPwd = EncryptUtils.encrypt(oldPwd);
        newPwd = EncryptUtils.encrypt(newPwd);
        confirmPwd = EncryptUtils.encrypt(confirmPwd);

        Long userId = UserHelper.getInstance().getUserInfo().getUserId();
        getModel().updatePassword(userId, oldPwd, newPwd, confirmPwd)
                .subscribe(new BaseObserver<BaseResult<Object>>() {
                    @Override
                    public void onNext(BaseResult<Object> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRequestSuccess(result.getMsg());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
