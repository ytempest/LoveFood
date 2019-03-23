package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.ActivityDetailInfo;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.ActivityDetailContract;
import com.ytempest.lovefood.mvp.model.ActivityDetailModel;
import com.ytempest.lovefood.util.ResultUtils;

import java.util.concurrent.CountDownLatch;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/3/21
 */
@InjectModel(ActivityDetailModel.class)
public class ActivityDetailPresenter extends BasePresenter<ActivityDetailContract.ActivityDetailView, ActivityDetailContract.Model>
        implements ActivityDetailContract.Presenter {

    private ActivityDetailInfo detailInfo;
    private Boolean isUserPartakeActivity;

    @Override
    public void getActivityDetail(long actId) {
        getView().onRequestStart(null);

        CountDownLatch latch = new CountDownLatch(2);

        detailInfo = null;
        isUserPartakeActivity = null;

        Schedulers.io().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                getModel().getActivityDetail(actId)
                        .subscribe(new BaseObserver<BaseResult<ActivityDetailInfo>>() {
                            @Override
                            public void onNext(BaseResult<ActivityDetailInfo> result) {
                                super.onNext(result);
                                int code = result.getCode();
                                if (code == ResultUtils.SUCCESS) {
                                    detailInfo = result.getData();
                                }
                                latch.countDown();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                latch.countDown();
                            }
                        });

                getModel().isUserPartakeActivity(actId)
                        .subscribe(new BaseObserver<BaseResult<Boolean>>() {
                            @Override
                            public void onNext(BaseResult<Boolean> result) {
                                super.onNext(result);
                                int code = result.getCode();
                                if (code == ResultUtils.SUCCESS) {
                                    isUserPartakeActivity = result.getData();
                                }
                                latch.countDown();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                latch.countDown();
                            }
                        });
                try {
                    latch.await();
                    AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                        @Override
                        public void run() {
                            if (detailInfo != null && isUserPartakeActivity != null) {
                                getView().onGetActivityDetailSuccess(detailInfo, isUserPartakeActivity);
                                getView().onRequestSuccess(null);

                            } else {
                                getView().onRequestFail("数据异常");
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
