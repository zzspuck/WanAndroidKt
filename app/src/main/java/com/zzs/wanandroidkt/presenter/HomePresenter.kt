package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.bean.LoginResponse

/**
 * 首页Presenter
 *
 * @author: zzs
 * @date: 2019/3/27
 */
public interface HomePresenter {

    interface onHomeListListener {

        fun getHomeList(page: Int = 0)

        fun getHomeListSuccess(result: HomeListResponse)

        fun getHomeListFailed(errorMessage: String?)
    }


    interface OnLoginListener{
        /**
         * login
         *
         * @param username 用户名
         * @param password 密码
         */
        fun loginWanAndroid(username:String, password:String)

        fun loginSuccess(result:LoginResponse)

        fun loginFailed(errorMessage:String?)
    }

    interface OnRegisterListener{
        fun registerWanAndroid(username:String, password:String, repassword:String)

        fun registerSuccess(result:LoginResponse)

        fun registerFailed(errorMessage:String?)
    }
}