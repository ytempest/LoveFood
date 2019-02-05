package com.ytempest.lovefood.http.observable;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ytempest
 *         Description：
 */
public class BaseObserver<Data> implements Observer<Data> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Data result) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        throw new RuntimeException("请求异常");
    }

    @Override
    public void onComplete() {

    }
}
