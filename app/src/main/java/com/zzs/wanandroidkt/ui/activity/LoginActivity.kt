package com.zzs.wanandroidkt.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseActivity
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.bean.LoginResponse
import com.zzs.wanandroidkt.presenter.LoginPresenterImpl
import com.zzs.wanandroidkt.toast
import com.zzs.wanandroidkt.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录界面
 *
 * @author: zzs
 * @date: 2019/5/30
 */
class LoginActivity : BaseActivity(), LoginView {

    /**
     * 检查登录状态
     */
    private var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private var user: String by Preference(Constant.USERNAME_KEY, "")

    private var pwd: String by Preference(Constant.PASSWORD_KEY, "")

    private val loginPresenter: LoginPresenterImpl by lazy {
        LoginPresenterImpl(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login.setOnClickListener(onClickListener)
        register.setOnClickListener(onClickListener)
        loginExit.setOnClickListener(onClickListener)
    }

    override fun cancelRequest() {
        loginPresenter.cancelRequest()
    }

    override fun loginSuccess(loginResponse: LoginResponse) {
        toast("登录成功")
    }

    override fun loginFailed(errorMessage: String?) {
        isLogin = false
        loginProgress.visibility = View.GONE
        errorMessage?.let {
            toast(it)
        }
    }

    override fun registerSuccess(loginResponse: LoginResponse) {
        toast("注册成功")
    }

    override fun registerFailed(errorMessage: String?) {
        isLogin = false
        loginProgress.visibility = View.GONE
        errorMessage?.let {
            toast(it)
        }
    }

    override fun loginRegisterAfter(result: LoginResponse) {
        isLogin = true
        user = result.data.username
        pwd = result.data.password
        loginProgress.visibility = View.GONE
        setResult(Activity.RESULT_OK,
            Intent().apply {
                putExtra(Constant.CONTENT_TITLE_KEY, result.data.username)
                finish()
            })
    }

    private val onClickListener = View.OnClickListener {
        when (it.id) {
            R.id.login -> {
                if (checkContent()) {
                    loginProgress.visibility = View.VISIBLE
                    loginPresenter.loginWanAndroid(
                        username.text.toString(),
                        password.text.toString()
                    )
                }
            }
            R.id.register -> {
                if (checkContent()) {
                    loginProgress.visibility = View.VISIBLE
                    loginPresenter.registerWanAndroid(
                        username.text.toString(),
                        password.text.toString(),
                        password.text.toString()
                    )
                }
            }
            R.id.loginExit -> {
                finish()
            }
        }
    }

    private fun checkContent(): Boolean {
        username.error = null
        password.error = null
        var cancel = false
        var focusView: View? = null

        val pwdText: String = password.text.toString()
        val usernameText: String = username.text.toString()

        if (TextUtils.isEmpty(pwdText)) {
            password.error = "密码不能为空"
            focusView = password
            cancel = true
        } else if (password.text.length < 6) {
            password.error = "密码长度不能小于6位"
            focusView = password
            cancel = true
        }

        // 检查用户名
        if (TextUtils.isEmpty(usernameText)) {
            username.error = "用户名不能为空"
            focusView = username
            cancel = true
        }

        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus()
            }
            return false
        } else {
            return true
        }
    }
}