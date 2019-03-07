package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.mvp.contract.CookbookListContract;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 *         Description：
 */
public class CookbookListModel extends BaseModel implements CookbookListContract.Model {

    @Override
    public Observable<BaseResult<DataList<BaseCookbook>>> getCookbookList(int pageNum, int pageSize, String group, String type) {
        return RetrofitClient.client().getService().getCookbookList(pageNum, pageSize, group, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
