package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.TopicContract;
import com.ytempest.lovefood.model.TopicModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(TopicModel.class)
public class TopicPresenter extends BasePresenter<TopicContract.View, TopicContract.Model> implements TopicContract.Presenter {
}
