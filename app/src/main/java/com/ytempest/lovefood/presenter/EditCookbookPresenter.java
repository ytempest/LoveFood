package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.EditCookbookContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.EditCookbookModel;
import com.ytempest.lovefood.model.PreviewCookbookModel;
import com.ytempest.lovefood.util.ResultUtils;

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
                        } else {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
