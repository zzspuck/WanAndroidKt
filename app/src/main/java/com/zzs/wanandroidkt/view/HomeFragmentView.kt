package com.zzs.wanandroidkt.view

import com.zzs.wanandroidkt.bean.HomeListResponse

/**
 * @author: zzs
 * @date: 2019/3/27
 */
interface HomeFragmentView {

    fun getHomeListSuccess(result: HomeListResponse)

    fun getHomeListFailed(errorMessage: String?)

    fun getHomListZero()

    fun getHomeListSmall(result: HomeListResponse)
}