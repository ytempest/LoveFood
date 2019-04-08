package com.ytempest.lovefood.mvp.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/3/31
 */
public interface TopicReleaseContract extends IContract {
    interface Presenter extends IPresenter {

        void releaseTopic(Map<String, RequestBody> partMap);
    }

    interface TopicReleaseView extends IView {

        void onReleaseTopicSuccess();
    }

    interface Model extends IModel {

        Observable<BaseResult<Object>> releaseTopic(Map<String, RequestBody> partMap);
    }
}
