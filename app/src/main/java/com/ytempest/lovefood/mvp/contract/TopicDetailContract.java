package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseComment;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CommentDetailInfo;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/3/7
 */
public interface TopicDetailContract extends IContract {
    interface Presenter extends IPresenter {

        void getCommentList(long topicId, int pageNum, int pageSize);

        void loadCommentList(Long topicId, int pageNum, int pageSize);

        void addComment(long topicId, String content, long fromUser, long toUser);
    }

    interface TopicDetailView extends IView {

        void onGetCommentListSuccess(DataList<CommentDetailInfo> data);

        void onGetCommentListFail(String msg);

        void onLoadCommentList(DataList<CommentDetailInfo> data);

        void onSendCommentSuccess(BaseComment comment);
    }

    interface Model extends IModel {

        Observable<BaseResult<DataList<CommentDetailInfo>>> getCommentList(long topicId, int pageNum, int pageSize);

        Observable<BaseResult<BaseComment>> addComment(long topicId, String content, long fromUser, long toUser);
    }
}
