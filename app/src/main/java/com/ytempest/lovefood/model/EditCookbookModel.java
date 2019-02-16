package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.contract.EditCookbookContract;
import com.ytempest.lovefood.contract.PreviewCookbookContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/16
 */
public class EditCookbookModel extends BaseModel implements EditCookbookContract.Model {

    @Override
    public Observable<BaseResult<CookbookInfo>> getCookbookInfo(long cookId) {
        return RetrofitClient.client().getService().getCookbookInfo(cookId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
