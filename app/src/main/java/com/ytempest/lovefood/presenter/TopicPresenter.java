package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.TopicResult;
import com.ytempest.lovefood.http.data.TopicResultTest;
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
    public void refreshTopicList(int pageNum, int pageSize) {

    }

    @Override
    public void loadTopicList(int pageNum, int pageSize) {
        getView().onRequestStart(null);

        getModel().getTopicList(pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<TopicResult>>() {
                    @Override
                    public void onNext(BaseResult<TopicResult> result) {
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
}
