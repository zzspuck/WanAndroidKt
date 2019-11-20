package com.zzs.wanandroidkt.retrofit

import com.zzs.wanandroidkt.bean.ArticleListResponse
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse
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
    ): Deferred<LoginResponse>

    @POST("/user/register")
    @FormUrlEncoded
    fun registerWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Deferred<LoginResponse>

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

    /**
     * 2.2 知识体系下的文章
     *
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page page
     * @param cid cid
     */
    @GET("/article/list/{page}/json")
    fun getArticleList(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): Deferred<ArticleListResponse>


    /**
     * 获取知识体系列表
     * https://www.wanandroid.com/tree/json
     */
    @GET("/tree/json")
    fun getKnorledgeSystemLIst(): Deferred<KnowledgeSystemResponse>

}