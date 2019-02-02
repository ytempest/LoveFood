package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.PersonalContract;
import com.ytempest.lovefood.model.PersonalModel;

/**
 * @author ytempest
 * @date 2019/2/1
 */
@InjectModel(PersonalModel.class)
public class PersonalPresenter extends BasePresenter<PersonalContract.PersonalView, PersonalContract.Model> implements PersonalContract.Presenter {
}
