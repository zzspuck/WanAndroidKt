package com.zzs.wanandroidkt.view

import com.zzs.wanandroidkt.bean.LoginResponse

/**
 * 登录View
 *
 * @author: zzs
 * @date: 2019/5/30
 */
interface LoginView {

    /**
     * 登录成功
     *
     * @param loginResponse 成功参数
     */
    fun loginSuccess(loginResponse: LoginResponse)

    /**
     * 登录失败
     *
     * @param errorMessage 错误原因
     */
    fun loginFailed(errorMessage: String?)

    /**
     * 注册成功
     *
     * @param loginResponse 参数
     */
    fun registerSuccess(loginResponse: LoginResponse)

    /**
     * 注册失败
     *
     * @param errorMessage 错误原因
     */
    fun registerFailed(errorMessage: String?)

    /**
     * login or register success after operation
     * @param result LoginResponse
     */
    fun loginRegisterAfter(result: LoginResponse)
}