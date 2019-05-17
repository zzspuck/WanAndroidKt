package com.zzs.wanandroidkt.model

import com.zzs.wanandroidkt.presenter.HomePresenter

/**
 * @author: zzs
 * @date: 2019/3/27
 */
interface HomeModel {


    fun getHomeList(onHomeListListener: HomePresenter.onHomeListListener, page: Int = 0)

    fun cancelHomeListRequest()

    fun loginWanAndroid(
        onLoginListener: HomePresenter.OnLoginListener,
        username: String,
        password: String
    )

    fun cancelLoginRequest()

    fun registerWandroid(
        onRegisterListener: HomePresenter.OnRegisterListener,
        username: String,
        password: String,
        repassword: String
    )

    fun cancelRegisterRequest()
}