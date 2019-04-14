package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.baselibrary.util.CustomThreadExecutor;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.PreviewCookbookContract;
import com.ytempest.lovefood.mvp.model.PreviewCookbookModel;
import com.ytempest.lovefood.util.ResultUtils;

import java.util.concurrent.CountDownLatch;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(PreviewCookbookModel.class)
public class PreviewCookbookPresenter extends BasePresenter<PreviewCookbookContract.PreviewCookbookView,
        PreviewCookbookContract.Model> implements PreviewCookbookContract.Presenter {

    private CookbookInfo mCookbookInfo;
    private Boolean isUserCollection;

    @Override
    public void getCookbookInfo(long cookId) {
        getView().onRequestStart(null);

        getModel().getCookbookInfo(cookId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult<CookbookInfo>>() {
                    @Override
                    public void onNext(BaseResult<CookbookInfo> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetCookbookInfo(result.getData(), null);

                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void getCookbookInfo(long cookId, long userId) {
        getView().onRequestStart(null);
        mCookbookInfo = null;
        isUserCollection = null;

        CountDownLatch latch = new CountDownLatch(2);

        CustomThreadExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                getModel().getCookbookInfo(cookId)
                        .subscribe(new BaseObserver<BaseResult<CookbookInfo>>() {
                            @Override
                            public void onNext(BaseResult<CookbookInfo> result) {
                                super.onNext(result);
                                int code = result.getCode();
                                if (code == ResultUtils.SUCCESS) {
                                    mCookbookInfo = result.getData();
                                }
                                latch.countDown();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                latch.countDown();
                            }
                        });

                getModel().judgeUserCollection(userId, cookId)
                        .subscribe(new BaseObserver<BaseResult<Boolean>>() {
                            @Override
                            public void onNext(BaseResult<Boolean> result) {
                                super.onNext(result);
                                int code = result.getCode();
                                if (code == ResultUtils.SUCCESS) {
                                    isUserCollection = result.getData();
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
                    CustomThreadExecutor.getInstance().runOnMain(new Runnable() {
                        @Override
                        public void run() {
                            if (mCookbookInfo != null && isUserCollection != null) {
                                getView().onGetCookbookInfo(mCookbookInfo, isUserCollection);
                                getView().onRequestSuccess(null);

                            } else {
                                getView().onRequestFail("数据异常");
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    getView().onRequestFail("数据异常");
                }
            }
        });
    }

    @Override
    public void collectionCookbook(long userId, long cookId) {
        getView().onRequestStart("加载中...");

        getModel().collectionCookbook(userId, cookId)
                .subscribe(new BaseObserver<BaseResult<Boolean>>() {
                    @Override
                    public void onNext(BaseResult<Boolean> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            Boolean data = result.getData();
                            if (data != null) {
                                getView().onCollectionCookbook(data);
                            }
                            getView().onRequestSuccess(result.getMsg());

                        } else {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
