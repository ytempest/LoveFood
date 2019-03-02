package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.TopicResult;
import com.ytempest.lovefood.http.data.TopicResultTest;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface TopicContract extends IContract {
    interface Presenter extends IPresenter {
        void refreshTopicList(int pageNum, int pageSize);

        void loadTopicList(int pageNum, int pageSize);
    }

    interface TopicView extends IView {
        void onGetTopicList(TopicResult data);
    }

    interface Model extends IModel {
        Observable<BaseResult<TopicResult>> getTopicList(int pageNum, int pageSize);
    }
}
