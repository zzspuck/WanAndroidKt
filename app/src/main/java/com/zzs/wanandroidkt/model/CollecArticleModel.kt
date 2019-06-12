package com.zzs.wanandroidkt.model

import com.zzs.wanandroidkt.presenter.HomePresenter

/**
 * @author: zzs
 * @date: 2019/6/12
 */
interface CollecArticleModel{
    /**
     * 收藏或者取消收藏
     *
     * @param onCollectArticleListener 监听
     * @param id 文章id
     * @param isAdd true:收藏 false：取消收藏
     */
    fun collectArticel(
        onCollectArticleListener: HomePresenter.OnCollectArticleListener,
        id:Int,
        isAdd:Boolean
    )

    fun cancelCollectRequest()
}