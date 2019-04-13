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
 * @date 2019/4/10
 */
public interface ReleaseCookbookContract extends IContract {
    interface Presenter extends IPresenter {

        void releaseCookbook(Map<String, RequestBody> map);

        void partakeActivityByCookbook(Map<String, RequestBody> map);
    }

    interface ReleaseCookbookView extends IView {

        void onReleasesCookbookSuccess();
    }

    interface Model extends IModel {
        Observable<BaseResult<Object>> releaseCookbook(Map<String, RequestBody> map);

        Observable<BaseResult<Object>> partakeActivityByCookbook(Map<String, RequestBody> map);
    }
}
