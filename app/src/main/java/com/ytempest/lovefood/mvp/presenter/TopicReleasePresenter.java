package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.model.TopicReleaseModel;
import com.ytempest.lovefood.util.ResultUtils;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(TopicReleaseModel.class)
public class TopicReleasePresenter extends BasePresenter<TopicReleaseContract.TopicReleaseView, TopicReleaseContract.Model>
        implements TopicReleaseContract.Presenter {

    @Override
    public void releaseTopic(Map<String, RequestBody> partMap) {
        getView().onRequestStart("发布中...");

        getModel().releaseTopic(partMap)
                .subscribe(new BaseObserver<BaseResult<Object>>() {
                    @Override
                    public void onNext(BaseResult<Object> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onRequestSuccess(result.getMsg());
                            getView().onReleaseTopicSuccess();
                        }
                        if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
