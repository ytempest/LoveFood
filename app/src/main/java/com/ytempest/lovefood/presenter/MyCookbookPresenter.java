package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.MyCookbookContract;
import com.ytempest.lovefood.data.BaseCookbook;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.MyCookbookModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(MyCookbookModel.class)
public class MyCookbookPresenter extends BasePresenter<MyCookbookContract.MyCookbookView, MyCookbookContract.Model>
        implements MyCookbookContract.Presenter {
    @Override
    public void getMyCookbookList(int pageNum, int pageSize) {

        getView().onRequestStart(null);

        long userId = UserHelper.getInstance().getUserInfo().getUserId();
        getModel().getMyCookbookList(userId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);

                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetCookbookList(result.getData());
                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
