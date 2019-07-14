package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.ArticleListResponse

/**
 * 对应类型列表Presenter
 *
 * @author: zzs
 * @date: 2019/7/13
 */
interface TypeArticlePresenter {

    /**
     * 获取对应类型列表接口
     *
     * @param page 当前页数
     * @param cid 当前类型文章列表ID
     */
    fun getTypeArticleList(page: Int = 0, cid: Int)

    interface onTypeArticleListListener {
        /**
         * 获取对应类型列表成功
         *
         * @return
         */
        fun getTypeArticeleListSuc(result: ArticleListResponse)

        /**
         * 对应类型文章列表获取失败
         *
         * @param errorMessage 错误信息
         */
        fun getTypeArticleListFail(errorMessage: String?)
    }
}