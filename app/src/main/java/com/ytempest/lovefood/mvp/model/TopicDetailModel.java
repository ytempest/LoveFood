package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseComment;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CommentDetailInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.mvp.contract.TopicDetailContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/3/7
 */
public class TopicDetailModel extends BaseModel implements TopicDetailContract.Model {

    @Override
    public Observable<BaseResult<DataList<CommentDetailInfo>>> getCommentList(long topicId, int pageNum, int pageSize) {
        return RetrofitClient.client().getService().getCommentList(topicId, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<BaseResult<BaseComment>> addComment(long topicId, String content, long fromUser, long toUser) {
        return RetrofitClient.client().getService().addComment(topicId, content, fromUser, toUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
