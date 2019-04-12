package com.ytempest.lovefood.http;

import com.ytempest.lovefood.http.data.ActivityDetailInfo;
import com.ytempest.lovefood.http.data.ActivityInfo;
import com.ytempest.lovefood.http.data.BaseComment;
import com.ytempest.lovefood.http.data.BaseCookbook;
import com.ytempest.lovefood.http.data.BaseResult;
import com.ytempest.lovefood.http.data.CommentDetailInfo;
import com.ytempest.lovefood.http.data.CookbookInfo;
import com.ytempest.lovefood.http.data.DataList;
import com.ytempest.lovefood.http.data.TopicInfo;
import com.ytempest.lovefood.http.data.UserInfo;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
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

    /**
     * 获取用户所有话题
     */
    @GET("user/topicList")
    Observable<BaseResult<DataList<TopicInfo>>> getUserTopicList(@Query("userId") Long userId,
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


    /*---------   话题接口   ---------*/

    @GET("topic/list")
    Observable<BaseResult<DataList<TopicInfo>>> getTopicList(@Query("pageNum") int pageNum,
                                                             @Query("pageSize") int pageSize);


    @GET("topic/info")
    Observable<BaseResult<DataList<CommentDetailInfo>>> getCommentList(@Query("topicId") long topicId,
                                                                       @Query("pageNum") int pageNum,
                                                                       @Query("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("topic/addComment")
    Observable<BaseResult<BaseComment>> addComment(@Field("topicId") long topicId,
                                                   @Field("content") String content,
                                                   @Field("fromUser") long fromUser,
                                                   @Field("toUser") long toUser);


    /**
     * 发布话题
     */
    @Multipart
    @POST("topic/addTopic")
    Observable<BaseResult<Object>> releaseTopic(@PartMap Map<String, RequestBody> partMap);



    /*---------   菜谱接口   ---------*/

    @GET("cook/list")
    Observable<BaseResult<DataList<BaseCookbook>>> getCookbookList(@Query("pageNum") int pageNum,
                                                                   @Query("pageSize") int pageSize,
                                                                   @Query("cookGroup") String group,
                                                                   @Query("cookType") String type);


    /**
     * 发布菜谱
     */
    @Multipart
    @POST("cook/addCook")
    Observable<BaseResult<Object>> releaseCookbook(@PartMap Map<String, RequestBody> partMap);

    /*---------   活动接口   ---------*/

    @GET("activity/list")
    Observable<BaseResult<DataList<ActivityInfo>>> getActivityList(@Query("pageNum") int pageNum,
                                                                   @Query("pageSize") int pageSize);

    @GET("activity/info")
    Observable<BaseResult<ActivityDetailInfo>> getActivityDetail(@Query("actId") long actId);

    /**
     * 判断用户是否参与了活动
     */
    @FormUrlEncoded
    @POST("activity/isPartake")
    Observable<BaseResult<Boolean>> isPartakeActivity(@Field("userId") long userId,
                                                      @Field("actId") long actId);

    @GET("activity/getPartakeCookList")
    Observable<BaseResult<DataList<BaseCookbook>>> getPartakeCookList(@Query("actId") long actId,
                                                                      @Query("pageNum") int pageNum,
                                                                      @Query("pageSize") int pageSize);
}
