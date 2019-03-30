package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.TopicReleaseContract;
import com.ytempest.lovefood.mvp.model.TopicReleaseModel;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(TopicReleaseModel.class)
public class TopicReleasePresenter extends BasePresenter<TopicReleaseContract.TopicReleaseView, TopicReleaseContract.Model>
        implements TopicReleaseContract.Presenter {

}
