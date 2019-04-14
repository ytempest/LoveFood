package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.MyPartakeContract;
import com.ytempest.lovefood.mvp.model.MyPartakeModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(MyPartakeModel.class)
public class MyPartakePresenter extends BasePresenter<MyPartakeContract.MyPartakeView, MyPartakeContract.Model>
        implements MyPartakeContract.Presenter {


    private final long mUserId;

    public MyPartakePresenter() {
        mUserId = UserHelper.getInstance().getUserInfo().getUserId();
    }

    @Override
    public void getPartakeList(int pageNum, int pageSize) {

        getView().onRequestStart(null);

        getModel().getMyPartakeList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseActivityInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseActivityInfo>> result) {
                        super.onNext(result);

                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetPartakeList(result.getData());
                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void refreshPartakeList(int pageNum, int pageSize) {
        getModel().getMyPartakeList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseActivityInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseActivityInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRefreshPartakeListSuccess(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRefreshPartakeListFail();
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void loadPartakeList(int pageNum, int pageSize) {
        getModel().getMyPartakeList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseActivityInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseActivityInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onLoadPartakeListSuccess(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
