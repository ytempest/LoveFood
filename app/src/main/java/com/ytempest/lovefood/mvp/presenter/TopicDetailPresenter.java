package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CommentInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.CookbookContract;
import com.ytempest.lovefood.mvp.contract.TopicDetailContract;
import com.ytempest.lovefood.mvp.model.TopicDetailModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/3/7
 */
@InjectModel(TopicDetailModel.class)
public class TopicDetailPresenter extends BasePresenter<TopicDetailContract.TopicDetailView, TopicDetailContract.Model>
        implements TopicDetailContract.Presenter {
    @Override
    public void getCommentList(long topicId, int pageNum, int pageSize) {
        getModel().getCommentList(topicId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<CommentInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<CommentInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onGetCommentListSuccess(result.getData());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onGetCommentListFail(result.getMsg());
                        }
                    }
                });
    }
}
