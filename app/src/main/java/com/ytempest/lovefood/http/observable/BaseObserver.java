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
    public void onNext(Data value) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
