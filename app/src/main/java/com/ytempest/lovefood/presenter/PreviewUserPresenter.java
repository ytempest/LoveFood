package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.PreviewUserModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(PreviewUserModel.class)
public class PreviewUserPresenter extends BasePresenter<PreviewUserContract.PreviewUserView, PreviewUserContract.Model>
        implements PreviewUserContract.Presenter {
    @Override
    public void requestUserInfo(long userId) {
        getView().onRequestStart(null);

        getModel().getUserInfo(userId)
                .subscribe(new BaseObserver<BaseResult<UserInfo>>() {
                    @Override
                    public void onNext(BaseResult<UserInfo> result) {
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRequestUserInfo(result.getData());
                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
