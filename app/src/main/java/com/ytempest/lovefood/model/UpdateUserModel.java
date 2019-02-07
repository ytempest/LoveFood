package com.ytempest.lovefood.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.contract.PreviewUserContract;
import com.ytempest.lovefood.contract.UpdateUserContract;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;
import com.ytempest.lovefood.http.RetrofitClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/2/1
 */
public class UpdateUserModel extends BaseModel implements UpdateUserContract.Model {

}
