package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.ActivityDetailContract;
import com.ytempest.lovefood.mvp.model.ActivityDetailModel;

/**
 * @author ytempest
 * @date 2019/3/21
 */
@InjectModel(ActivityDetailModel.class)
public class ActivityDetailPresenter extends BasePresenter<ActivityDetailContract.ActivityDetailView, ActivityDetailContract.Model>
        implements ActivityDetailContract.Presenter {

}
