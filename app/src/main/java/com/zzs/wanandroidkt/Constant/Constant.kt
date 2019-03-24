package com.zzs.wanandroidkt.Constant

import android.widget.Toast

/**
 * Constant
 *
 * @author: zzs
 * @date: 2019/3/7
 */
object Constant {

    const val REQUEST_BASE_URL = "http://wanandroid.com/"
    /**
     * Share preferences name
     */
    const val SHARED_NAME = "_preferences"

    /**
     * Debug
     */
    const val INTERCEPTOR_ENABLE = false

    const val LOGIN_KEY ="login"

    const val USERNAME_KEY = "username"
    const val PASSWORD_KEY= "password"

    /**
     * Toast
     */
    @JvmField
    var showToast: Toast? = null
}