package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.HomeListResponse

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
}