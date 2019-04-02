package com.zzs.wanandroidkt.model

import RetrofitHelper
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.bean.HomeListResponse
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