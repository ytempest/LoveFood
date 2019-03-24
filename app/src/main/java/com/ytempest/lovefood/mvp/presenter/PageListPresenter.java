package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.mvp.contract.PageListContract;
import com.ytempest.lovefood.mvp.model.PageListModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/3/22
 */
@InjectModel(PageListModel.class)
public class PageListPresenter extends BasePresenter<PageListContract.PageListView, PageListContract.Model>
        implements PageListContract.Presenter {

    @Override
    public void refreshPartakeCookList(long actId, int pageNum, int pageSize) {
        getModel().getPartakeCookList(actId, pageNum, pageSize)
                .subscribe(new BaseObserver<BaseResult<DataList<BaseCookbook>>>() {
                               @Override
                               public void onNext(BaseResult<DataList<BaseCookbook>> result) {
                                   super.onNext(result);
                                   int code = result.getCode();
                                   if (code == ResultUtils.SUCCESS) {
                                       getView().onGetPartakeCookListSuccess(result.getData());

                                   } else if (code == ResultUtils.ERROR) {
                                       getView().onGetPartakeCookListFail(result.getMsg());

                                   }
                               }
                           }
                );
    }
}
