package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.CookbookContract;
import com.ytempest.lovefood.mvp.contract.TopicDetailContract;
import com.ytempest.lovefood.mvp.model.TopicDetailModel;

/**
 * @author ytempest
 * @date 2019/3/7
 */
@InjectModel(TopicDetailModel.class)
public class TopicDetailPresenter extends BasePresenter<TopicDetailContract.TopicDetailView, TopicDetailContract.Model>
        implements TopicDetailContract.Presenter {
}
