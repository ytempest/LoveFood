package com.ytempest.lovefood.http;

import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.UserInfo;

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
    Observable<BaseResult<UserInfo>> updateUserInfo(@PartMap Map<String, RequestBody> partMap);

    /**
     * 修改用户密码
     */
    @FormUrlEncoded
    @POST("user/updatePwd")
    Observable<BaseResult<Object>> updatePassword(@Field("userId") Long userId,
                                                  @Field("oldPwd") String oldPwd,
                                                  @Field("newPwd") String newPwd,
                                                  @Field("confirmPwd") String confirmPwd);


    /**
     * 获取用户所有菜谱
     */
    @GET("user/cookList")
    Observable<BaseResult<DataList<BaseCookbook>>> getUserCookList(@Query("userId") Long userId,
                                                                   @Query("pageNum") Integer pageNum,
                                                                   @Query("pageSize") Integer pageSize);


    /*---------   菜谱接口   ---------*/


    /**
     * 获取菜谱的详细信息
     */
    @GET("cook/info")
    Observable<BaseResult<CookbookInfo>> getCookbookInfo(@Query("cookId") Long cookId);

    /**
     * 修改菜谱
     */
    @Multipart
    @POST("cook/updateCook")
    Observable<BaseResult<CookbookInfo>> updateCookInfo(@PartMap Map<String, RequestBody> partMap);
}
