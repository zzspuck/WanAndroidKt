package com.zzs.wanandroidkt.model

import RetrofitHelper
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.bean.LoginResponse
import com.zzs.wanandroidkt.cancelByActivity
import com.zzs.wanandroidkt.presenter.HomePresenter
import com.zzs.wanandroidkt.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * HomeModel实现类
 *
 * @author: zzs
 * @date: 2019/3/30
 */
class HomeModelImpl : HomeModel {
    private var homeListAsync: Deferred<HomeListResponse>? = null
    private var loginWanAndroidAsync: Deferred<LoginResponse>? = null

    private var registerWanAndroidAsync:Deferred<LoginResponse>?= null

    override fun loginWanAndroid(onLoginListener: HomePresenter.OnLoginListener, username: String, password: String) {
        CoroutineScope(Dispatchers.Main).launch {
            tryCatch({
                it.printStackTrace()
                onLoginListener.loginFailed(it.toString())
            }, {
                loginWanAndroidAsync?.cancelByActivity()
                loginWanAndroidAsync = RetrofitHelper.retrofitService.loginWanAndroid(username, password)
                val result = loginWanAndroidAsync?.await()
                result ?: let {
                    onLoginListener.loginFailed(Constant.RESULT_NULL)
                    return@launch
                }
                onLoginListener.loginSuccess(result)
            })
        }
    }

    override fun cancelLoginRequest() {
        loginWanAndroidAsync?.cancelByActivity()
    }

    override fun registerWandroid(
        onRegisterListener: HomePresenter.OnRegisterListener,
        username: String,
        password: String,
        repassword: String
    ) {
      CoroutineScope(Dispatchers.Main).launch {
          tryCatch({
              it.printStackTrace()
              onRegisterListener.registerFailed(it.toString())
          }, {
              registerWanAndroidAsync?.cancelByActivity()
              registerWanAndroidAsync = RetrofitHelper.retrofitService.registerWanAndroid(username, password, repassword)
              val await = registerWanAndroidAsync?.await()
              await?:let {
                  onRegisterListener.registerFailed(Constant.RESULT_NULL)
                  return@launch
              }
              onRegisterListener.registerSuccess(await)
          })
      }
    }

    override fun cancelRegisterRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeList(onHomeListListener: HomePresenter.onHomeListListener, page: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            tryCatch({
                it.printStackTrace()
                onHomeListListener.getHomeListFailed(it.toString())
            }) {
                homeListAsync?.cancelByActivity()
                homeListAsync = RetrofitHelper.retrofitService.getHomeList(page)
                val result = homeListAsync?.await()
                result ?: let {
                    onHomeListListener.getHomeListFailed(Constant.RESULT_NULL)
                    return@launch
                }
                onHomeListListener.getHomeListSuccess(result)
            }
        }
    }

    override fun cancelHomeListRequest() {
        homeListAsync?.cancelByActivity()
    }

}