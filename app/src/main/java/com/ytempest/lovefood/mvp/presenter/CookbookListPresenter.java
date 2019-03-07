package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.CookbookListContract;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.model.CookbookListModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 *         Description：
 */
@InjectModel(CookbookListModel.class)
public class CookbookListPresenter extends BasePresenter<CookbookListContract.CookbookListView, CookbookListContract.Model>
        implements CookbookListContract.Presenter {
    @Override
    public void getCookbookList(int pageNum, int pageSize, String group, String type) {
        getView().onRequestStart(null);

        getModel().getCookbookList(pageNum, pageSize, group, type)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetCookbookList(result.getData());
                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onFailGetCookbookList(result.getMsg());
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void refreshCookbookList(int pageNum, int pageSize, String group, String type) {

    }

    @Override
    public void loadCookbookList(int pageNum, int pageSize, String group, String type) {

    }
}
