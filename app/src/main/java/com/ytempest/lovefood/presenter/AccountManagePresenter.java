package com.ytempest.lovefood.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.contract.AccountManageContract;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.model.AccountManageModel;
import com.ytempest.lovefood.model.UpdateUserModel;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(AccountManageModel.class)
public class AccountManagePresenter extends BasePresenter<AccountManageContract.AccountManagerView, AccountManageContract.Model>
        implements AccountManageContract.Presenter {

}
