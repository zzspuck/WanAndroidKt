package com.zzs.wanandroidkt.Constant

import android.widget.Toast

/**
 * Constant
 *
 * @author: zzs
 * @date: 2019/3/7
 */
object Constant {

    const val REQUEST_BASE_URL = "https://wanandroid.com/"
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

    /**
     * result null
     */
    const val RESULT_NULL = "result null!"

    const val MAIN_REQUEST_CODE = 100

    /**
     * url key
     */
    const val CONTENT_URL_KEY = "url"
    /**
     * title key
     */
    const val CONTENT_TITLE_KEY = "title"

    /**
     * id key
     */
    const val CONTENT_ID_KEY = "id"

    /**
     * share key
     */
    const val CONTENT_SHARE_TYPE = "text/plain"
}