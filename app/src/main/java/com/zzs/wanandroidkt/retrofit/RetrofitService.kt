package com.zzs.wanandroidkt.retrofit

import com.zzs.wanandroidkt.bean.HomeListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 借口类
 * @author: zzs
 * @date: 2019/3/14
 */
interface RetrofitService {

    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") page: Int): Deferred<HomeListResponse>

}