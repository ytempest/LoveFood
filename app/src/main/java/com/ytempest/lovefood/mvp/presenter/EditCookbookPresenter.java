package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.data.UserInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.EditCookbookContract;
import com.ytempest.lovefood.mvp.model.EditCookbookModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/16
 */
@InjectModel(EditCookbookModel.class)
public class EditCookbookPresenter extends BasePresenter<EditCookbookContract.EditCookbookView,
        EditCookbookContract.Model> implements EditCookbookContract.Presenter {

    @Override
    public void getCookInfo(long cookId) {
        getView().onRequestStart(null);

        getModel().getCookbookInfo(cookId)
                .subscribe(new BaseObserver<BaseResult<CookbookInfo>>() {
                    @Override
                    public void onNext(BaseResult<CookbookInfo> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRequestSuccess(null);

                            getView().onGetCookbookInfo(result.getData());
                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void saveCookbookInfo(Map<String, RequestBody> map) {
        getView().onRequestStart("正在保存...");
        getModel().saveCookbookInfo(map).subscribe(new BaseObserver<BaseResult<CookbookInfo>>() {
            @Override
            public void onNext(BaseResult<CookbookInfo> result) {
                super.onNext(result);
                int code = result.getCode();
                if (code == ResultUtils.SUCCESS) {
                    getView().onSaveCookbookInfoSuccess(result.getData());
                    getView().onRequestSuccess(result.getMsg());

                } else if (code == ResultUtils.ERROR) {
                    getView().onRequestFail(result.getMsg());
                }
            }
        });
    }

    @Override
    public UserInfo getUserInfo() {
        return UserHelper.getInstance().getUserInfo();
    }
}
