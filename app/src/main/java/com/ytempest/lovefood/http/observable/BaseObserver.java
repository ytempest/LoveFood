package com.ytempest.lovefood.http.observable;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ytempest
 *         Description：
 */
public class BaseObserver<Data> implements Observer<Data> {

    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Data result) {
        Log.d(TAG, "onNext: result：" + result);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
