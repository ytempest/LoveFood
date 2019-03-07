package com.ytempest.lovefood.mvp.presenter;

import com.ytempest.baselibrary.base.mvp.BasePresenter;
import com.ytempest.baselibrary.base.mvp.inject.InjectModel;
import com.ytempest.lovefood.mvp.contract.AccountManageContract;
import com.ytempest.lovefood.mvp.model.AccountManageModel;

/**
 * @author ytempest
 * @date 2019/2/7
 */
@InjectModel(AccountManageModel.class)
public class AccountManagePresenter extends BasePresenter<AccountManageContract.AccountManagerView, AccountManageContract.Model>
        implements AccountManageContract.Presenter {

}
