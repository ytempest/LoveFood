package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.PageListContract;
import com.ytempest.lovefood.mvp.model.PageListModel;

/**
 * @author ytempest
 * @date 2019/3/22
 */
@InjectModel(PageListModel.class)
public class PageListPresenter extends BasePresenter<PageListContract.PageListView, PageListContract.Model>
        implements PageListContract.Presenter {

}
