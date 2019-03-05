package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.ActivityContract;
import com.ytempest.lovefood.model.ActivityModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(ActivityModel.class)
public class ActivityPresenter extends BasePresenter<ActivityContract.ActivityView, ActivityContract.Model> implements ActivityContract.Presenter {
    @Override
    public void loadActivityList(int pageNum, int pageSize) {

    }
}
