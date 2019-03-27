package com.zzs.wanandroidkt.model

import com.zzs.wanandroidkt.presenter.HomePresenter

/**
 * @author: zzs
 * @date: 2019/3/27
 */
interface HomeModel {


    fun getHomeList(onHomeListListener: HomePresenter.onHomeListListener, page: Int = 0)

    fun cancelHomeListRequest()
}