package com.ytempest.lovefood.contract;

import com.ytempest.baselibrary.base.mvp.IContract;
import com.ytempest.baselibrary.base.mvp.IModel;
import com.ytempest.baselibrary.base.mvp.IPresenter;
import com.ytempest.baselibrary.base.mvp.IView;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.data.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author ytempest
 * @date 2019/2/16
 */
public interface EditCookbookContract extends IContract {
    interface Presenter extends IPresenter {

        void getCookInfo(long cookId);

        void saveCookbookInfo(Map<String, RequestBody> map);

        UserInfo getUserInfo();
    }

    interface EditCookbookView extends IView {

        void onGetCookbookInfo(CookbookInfo data);

        void onSaveCookbookInfoSuccess(CookbookInfo data);
    }

    interface Model extends IModel {
        Observable<BaseResult<CookbookInfo>> getCookbookInfo(long cookId);

        Observable<BaseResult<CookbookInfo>> saveCookbookInfo(Map<String, RequestBody> map);
    }
}
