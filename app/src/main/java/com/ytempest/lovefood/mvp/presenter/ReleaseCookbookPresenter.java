package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.AccountManageContract;
import com.ytempest.lovefood.mvp.contract.ReleaseCookbookContract;
import com.ytempest.lovefood.mvp.model.ReleaseCookbookModel;
import com.ytempest.lovefood.util.ResultUtils;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(ReleaseCookbookModel.class)
public class ReleaseCookbookPresenter extends BasePresenter<ReleaseCookbookContract.ReleaseCookbookView, ReleaseCookbookContract.Model>
        implements ReleaseCookbookContract.Presenter {

    @Override
    public void releaseCookbook(Map<String, RequestBody> map) {
        getView().onRequestStart("添加中...");

        getModel().releaseCookbook(map).subscribe(new BaseObserver<BaseResult<Object>>() {
            @Override
            public void onNext(BaseResult<Object> result) {
                super.onNext(result);
                int code = result.getCode();
                if (code == ResultUtils.SUCCESS) {
                    getView().onRequestSuccess(result.getMsg());
                    getView().onReleasesCookbookSuccess();

                } else if (code == ResultUtils.ERROR) {
                    getView().onRequestFail(result.getMsg());
                }

            }
        });
    }
}
