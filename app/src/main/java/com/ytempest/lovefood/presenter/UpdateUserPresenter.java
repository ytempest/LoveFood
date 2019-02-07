package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.observable.BaseObserver;
import com.ytempest.lovefood.model.PreviewUserModel;
import com.ytempest.lovefood.model.UpdateUserModel;
import com.ytempest.lovefood.util.ResultUtils;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(UpdateUserModel.class)
public class UpdateUserPresenter extends BasePresenter<UpdateUserContract.UpdateUserView, UpdateUserContract.Model>
        implements UpdateUserContract.Presenter {

}
