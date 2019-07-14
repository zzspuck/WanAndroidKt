package com.zzs.wanandroidkt.view

import com.zzs.wanandroidkt.bean.ArticleListResponse

/**
 * 想要类型文章列表View
 *
 * @author: zzs
 * @date: 2019/7/13
 */
interface TypeArticleView {

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