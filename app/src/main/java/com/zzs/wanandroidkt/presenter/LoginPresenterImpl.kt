package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.LoginResponse
import com.zzs.wanandroidkt.model.HomeModel
import com.zzs.wanandroidkt.model.HomeModelImpl
import com.zzs.wanandroidkt.view.LoginView

/**
 * 登录Presenter
 *
 * @author: zzs
 * @date: 2019/5/30
 */
class LoginPresenterImpl(private val loginView: LoginView) : HomePresenter.OnLoginListener,
    HomePresenter.OnRegisterListener {

    private val homeModel: HomeModel = HomeModelImpl()

    override fun registerWanAndroid(username: String, password: String, repassword: String) {
        homeModel.registerWanAndroid(this, username, password, repassword)
    }

    override fun registerSuccess(result: LoginResponse) {
        if (result.errorCode !=0) {
            loginView.registerFailed(result.errorMsg)
        } else{
            loginView.registerSuccess(result)
            loginView.loginRegisterAfter(result)
        }
    }

    override fun registerFailed(errorMessage: String?) {
        loginView.registerFailed(errorMessage)
    }

    override fun loginWanAndroid(username: String, password: String) {
        homeModel.loginWanAndroid(this, username, password)
    }

    override fun loginSuccess(result: LoginResponse) {

        if (result.errorCode !=0) {
            loginView.loginFailed(result.errorMsg)
        } else{
            loginView.loginSuccess(result)
            loginView.loginRegisterAfter(result)
        }
    }

    override fun loginFailed(errorMessage: String?) {
        loginView.loginFailed(errorMessage)
    }

    fun cancelRequest(){
        homeModel.cancelLoginRequest()
        homeModel.cancelRegisterRequest()
    }
}