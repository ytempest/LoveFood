package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.TopicModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(TopicModel.class)
public class TopicPresenter extends BasePresenter<TopicContract.TopicView, TopicContract.Model> implements TopicContract.Presenter {
    @Override
    public void getTopicList(int pageNum, int pageSize) {
        getView().onRequestStart(null);

        getModel().getTopicList(pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<TopicInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<TopicInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetTopicList(result.getData());
                            getView().onRequestSuccess(null);

                        } else {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void refreshTopicList(int pageNum, int pageSize) {
        getModel().getTopicList(pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<TopicInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<TopicInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRefreshTopicList(result.getData());

                        } else {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }

    @Override
    public void loadTopicList(int pageNum, int pageSize) {
        getModel().getTopicList(pageNum, pageSize)
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
