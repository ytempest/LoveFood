package com.ytempest.lovefood.http;

import com.ytempest.lovefood.util.ThreadExecutor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ytempest
 *         Description：
 */
public class RetrofitClient {

    private static final String URL = "http://192.168.31.68:8080/";

    private static RetrofitClient INSTANCE = null;
    private Retrofit mRetrofit;

    static {
        INSTANCE = new RetrofitClient();
    }

    private final ApiService mApiService;

    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .callbackExecutor(ThreadExecutor.getExecutor())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitClient client() {
        return INSTANCE;
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public String getUrl() {
        return URL;
    }

    public ApiService getService() {
        return mApiService;
    }


}
