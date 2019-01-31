package com.ytempest.lovefood.fragment;

import com.ytempest.baselibrary.base.BaseFragment;
import com.ytempest.lovefood.R;
import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.http.ApiService;
import com.ytempest.lovefood.http.RetrofitClient;
import com.ytempest.lovefood.http.RetrofitUtils;
import com.ytempest.lovefood.http.observable.BaseObserver;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * @author ytempest
 *         Description：
 */
public class TopicFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        RetrofitClient.client().getService().login("", "")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResult>() {
                    @Override
                    public void onNext(BaseResult value) {
                        super.onNext(value);
                    }
                });
    }
}

