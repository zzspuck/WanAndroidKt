package com.zzs.wanandroidkt.bean

/**
 * 文章列表返回
 *
 * @author zzs
 *
 */
data class ArticleListResponse(
    var errorCode:Int,
    var errorMsg:String?,
    var data: Data
)
