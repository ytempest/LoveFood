package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.MyCookbookContract;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
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


    private final long mUserId;

    public MyCookbookPresenter() {
        mUserId = UserHelper.getInstance().getUserInfo().getUserId();
    }

    @Override
    public void getMyCookbookList(int pageNum, int pageSize) {

        getView().onRequestStart(null);

        getModel().getMyCookbookList(mUserId, pageNum, pageSize)
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

    @Override
    public void refreshCookbookList(int pageNum, int pageSize) {
        getModel().getMyCookbookList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRefreshCookbookList(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void loadCookbookList(int pageNum, int pageSize) {
        getModel().getMyCookbookList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onLoadCookbookList(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
