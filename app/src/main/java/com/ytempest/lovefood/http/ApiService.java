package com.ytempest.lovefood.http;

import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author ytempest
 *         Description：
 */
public interface ApiService {

    /*---------   用户接口   ---------*/

    /**
     * 用户登录
     *
     * @param account  用户名或者手机号码
     * @param password 已经加密后的密码
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseResult<UserInfo>> login(@Field("account") String account,
                                           @Field("password") String password);

    /**
     * 注册用户
     */
    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseResult<UserInfo>> register(@Field("account") String account,
                                              @Field("pwd") String password,
                                              @Field("phone") String phone);


}
