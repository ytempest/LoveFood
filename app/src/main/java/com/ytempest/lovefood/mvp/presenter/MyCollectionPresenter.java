package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.MyCollectionContract;
import com.ytempest.lovefood.mvp.model.MyCollectionModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(MyCollectionModel.class)
public class MyCollectionPresenter extends BasePresenter<MyCollectionContract.MyCollectionView, MyCollectionContract.Model>
        implements MyCollectionContract.Presenter {


    private final long mUserId;

    public MyCollectionPresenter() {
        mUserId = UserHelper.getInstance().getUserInfo().getUserId();
    }

    @Override
    public void getMyCollectionList(int pageNum, int pageSize) {

        getView().onRequestStart(null);

        getModel().getMyCollectionList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);

                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetCollectionList(result.getData());
                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void refreshCollectionList(int pageNum, int pageSize) {
        getModel().getMyCollectionList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRefreshCollectionListSuccess(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRefreshCollectionListFail();
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void loadCollectionList(int pageNum, int pageSize) {
        getModel().getMyCollectionList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onLoadCollectionListSuccess(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
