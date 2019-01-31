package com.ytempest.lovefood.http;

import com.ytempest.lovefood.data.BaseResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author ytempest
 *         Description：
 */
public interface ApiService {

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResult> login(@Field("password") String password,
                                 @Field("userType") String userType);

}
