package com.zzs.wanandroidkt.retrofit

import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.bean.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * 借口类
 * @author: zzs
 * @date: 2019/3/14
 */
interface RetrofitService {

    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int): Deferred<HomeListResponse>



    @FormUrlEncoded
    @POST("/user/login")
    fun loginWanAndroid(
        @Field("username") usrname: String,
        @Field("password") password: String
    ):Deferred<LoginResponse>

    @POST("/user/register")
    @FormUrlEncoded
    fun registerWanAndroid(
        @Field("username") username:String,
        @Field("password") password: String,
        @Field("repassword") repassword:String
    ):Deferred<LoginResponse>

    /**
     * 收藏文章
     * @param id id
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/collect/{id}/json")
    fun addCollectArticle(
        @Path("id") id: Int
    ): Deferred<HomeListResponse>

    /**
     * 删除收藏文章
     * @param id id
     * @param originId -1
     * @return Deferred<HomeListResponse>
     */
    @POST("/lg/uncollect/{id}/json")
    @FormUrlEncoded
    fun removeCollectArticle(
        @Path("id") id: Int,
        @Field("originId") originId: Int = -1
    ): Deferred<HomeListResponse>
}