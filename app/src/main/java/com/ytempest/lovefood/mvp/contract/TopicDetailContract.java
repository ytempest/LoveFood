package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CommentInfo;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/3/7
 */
public interface TopicDetailContract extends IContract {
    interface Presenter extends IPresenter {

        void getCommentList(long topicId, int pageNum, int pageSize);
    }

    interface TopicDetailView extends IView {

        void onGetCommentListSuccess(DataList<CommentInfo> data);

        void onGetCommentListFail(String msg);
    }

    interface Model extends IModel {

        Observable<BaseResult<DataList<CommentInfo>>> getCommentList(long topicId, int pageNum, int pageSize);
    }
}
