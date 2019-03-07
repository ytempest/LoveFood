package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.ActivityContract;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.model.ActivityModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(ActivityModel.class)
public class ActivityPresenter extends BasePresenter<ActivityContract.ActivityView, ActivityContract.Model> implements ActivityContract.Presenter {
    @Override
    public void getActivityList(int pageNum, int pageSize) {
        getModel().getActivityList(pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<ActivityInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<ActivityInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetActivityList(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            // TODO: 2019/03/05
                        }
                    }
                });
    }

    @Override
    public void loadActivityList(int pageNum, int pageSize) {
        getModel().getActivityList(pageNum, pageSize).subscribe(new BaseObserver<BaseResult<DataList<ActivityInfo>>>() {
            @Override
            public void onNext(BaseResult<DataList<ActivityInfo>> result) {
                super.onNext(result);
                int code = result.getCode();
                if (code == ResultUtils.SUCCESS) {
                    getView().onLoadActivityList(result.getData());

                } else if (code == ResultUtils.ERROR) {
                    getView().onRequestFail(result.getMsg());
                }
            }
        });
    }

    @Override
    public void refreshActivityList(int pageNum, int pageSize) {
        getModel().getActivityList(pageNum, pageSize).subscribe(new BaseObserver<BaseResult<DataList<ActivityInfo>>>() {
            @Override
            public void onNext(BaseResult<DataList<ActivityInfo>> result) {
                super.onNext(result);
                int code = result.getCode();
                if (code == ResultUtils.SUCCESS) {
                    getView().onRefreshActivityList(result.getData());

                } else if (code == ResultUtils.ERROR) {
                    getView().onRequestFail(result.getMsg());
                }
            }
        });
    }
}
