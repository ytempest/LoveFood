package com.ytempest.lovefood.mvp.model;

import com.ytempest.baselibrary.base.mvp.BaseModel;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.mvp.contract.TopicContract;
import com.ytempest.lovefood.mvp.contract.TopicDetailContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author ytempest
 * @date 2019/3/7
 */
public class TopicDetailModel extends BaseModel implements TopicDetailContract.Model {

}
