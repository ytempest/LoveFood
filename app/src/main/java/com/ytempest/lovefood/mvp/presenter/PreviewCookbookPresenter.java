package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.PreviewCookbookContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.model.PreviewCookbookModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(PreviewCookbookModel.class)
public class PreviewCookbookPresenter extends BasePresenter<PreviewCookbookContract.PreviewCookbookView,
        PreviewCookbookContract.Model> implements PreviewCookbookContract.Presenter {
    @Override
    public void getCookbookInfo(long cookId) {
        getView().onRequestStart(null);

        getModel().getCookbookInfo(cookId)
                .subscribe(new BaseObserver<BaseResult<CookbookInfo>>() {
                    @Override
                    public void onNext(BaseResult<CookbookInfo> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetCookbookInfo(result.getData());

                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
