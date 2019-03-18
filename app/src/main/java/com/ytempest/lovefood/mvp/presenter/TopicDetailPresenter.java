package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.baselibrary.util.LogUtils;
import com.ytempest.lovefood.http.data.BaseComment;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CommentDetailInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
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
                .subscribe(new BaseObserver<BaseResult<DataList<CommentDetailInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<CommentDetailInfo>> result) {
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

    @Override
    public void loadCommentList(Long topicId, int pageNum, int pageSize) {
        getModel().getCommentList(topicId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<CommentDetailInfo>>>() {
                    @Override
                    public void onNext(BaseResult<DataList<CommentDetailInfo>> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onLoadCommentList(result.getData());
                        }
                    }
                });
    }

    @Override
    public void addComment(long topicId, String content, long fromUser, long toUser) {
        getView().onRequestStart("发送中...");
        getModel().addComment(topicId, content, fromUser, topicId)
                .subscribe(new BaseObserver<BaseResult<BaseComment>>() {
                    @Override
                    public void onNext(BaseResult<BaseComment> result) {
                        super.onNext(result);
                        int code = result.getCode();
                        if (code == ResultUtils.SUCCESS) {
                            getView().onSendCommentSuccess(result.getData());
                            getView().onRequestSuccess(result.getMsg());

                        } else if (code == ResultUtils.ERROR) {
                            getView().onRequestFail(result.getMsg());
                        }
                    }
                });
    }
}
