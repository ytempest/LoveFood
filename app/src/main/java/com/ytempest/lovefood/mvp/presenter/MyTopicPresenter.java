package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.MyTopicContract;
import com.ytempest.lovefood.mvp.model.MyTopicModel;
import com.ytempest.lovefood.util.ResultUtils;
import com.ytempest.lovefood.util.UserHelper;

/**
 * @author ytempest
 * @date 2019/2/9
 */
@InjectModel(MyTopicModel.class)
public class MyTopicPresenter extends BasePresenter<MyTopicContract.MyTopicView, MyTopicContract.Model>
        implements MyTopicContract.Presenter {


    private final long mUserId;

    public MyTopicPresenter() {
        mUserId = UserHelper.getInstance().getUserInfo().getUserId();
    }

    @Override
    public void getMyTopicList(int pageNum, int pageSize) {

        getView().onRequestStart(null);

        getModel().getMyTopicList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<TopicInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<TopicInfo>> result) {
                        super.onNext(result);

                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetTopicList(result.getData());
                            getView().onRequestSuccess(null);

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void refreshTopicList(int pageNum, int pageSize) {
        getModel().getMyTopicList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<TopicInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<TopicInfo>> result) {
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
    public void loadTopicList(int pageNum, int pageSize) {
        getModel().getMyTopicList(mUserId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<TopicInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<TopicInfo>> result) {
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
