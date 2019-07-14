package com.zzs.wanandroidkt.model

import com.zzs.wanandroidkt.presenter.TypeArticlePresenter

/**
 * 对应文章列表Model
 *
 * @author: zzs
 * @date: 2019/7/13
 */
interface TypeArticleModel {

    /**
     * 获取文章列表
     *
     * @param onTypeArticleListListener 获取结果监听
     * @param page 页数
     * @param cid 类型id
     */
    fun getTypeArticleList(
        onTypeArticleListListener: TypeArticlePresenter.onTypeArticleListListener, page: Int,
        cid: Int
    )

    /**
     * 取消网络请求
     */
    fun cancelRequest()
}