package com.ytempest.lovefood.http;

import com.ytempest.lovefood.data.BaseResult;
import com.ytempest.lovefood.data.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

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

    /**
     * 根据用户Id获取用户的个人信息
     */
    @GET("user/info")
    Observable<BaseResult<UserInfo>> getUserInfo(@Query("userId") long userId);

    /**
     * 更新用户信息
     */
    @Multipart
    @POST("user/updateInfo")
    Observable<BaseResult<UserInfo>> updateUserInfo(@Part MultipartBody.Part headPart,
                                                    @PartMap Map<String, RequestBody> partMap);

    /**
     * 修改用户密码
     */
    @FormUrlEncoded
    @POST("user/updatePwd")
    Observable<BaseResult<Object>> updatePassword(@Field("userId") Long userId,
                                                  @Field("oldPwd") String oldPwd,
                                                  @Field("newPwd") String newPwd,
                                                  @Field("confirmPwd") String confirmPwd);


}
