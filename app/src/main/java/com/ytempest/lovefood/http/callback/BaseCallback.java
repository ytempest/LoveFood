package com.ytempest.lovefood.http.callback;

import com.ytempest.baselibrary.util.CustomThreadExecutor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author ytempest
 *         Description：
 */
public abstract class BaseCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        final T result = response.body();

        CustomThreadExecutor.getInstance().runOnMain(new Runnable() {
            @Override
            public void run() {
                onSuccess(result);
            }
        });
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onError(t);
    }

    public abstract void onSuccess(T result);

    public abstract void onError(Throwable t);
}