package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;

import io.reactivex.Observable;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public interface TopicContract extends IContract {
    interface Presenter extends IPresenter {
        void getTopicList(int pageNum, int pageSize);

        void refreshTopicList(int pageNum, int pageSize);

        void loadTopicList(int pageNum, int pageSize);
    }

    interface TopicView extends IView {
        void onGetTopicList(DataList<TopicInfo> data);

        void onRefreshTopicList(DataList<TopicInfo> data);

        void onLoadCookbookList(DataList<TopicInfo> data);
    }

    interface Model extends IModel {
        Observable<BaseResult<DataList<TopicInfo>>> getTopicList(int pageNum, int pageSize);
    }
}
