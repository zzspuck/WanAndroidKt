package com.zzs.wanandroidkt.bean

/**
 * 首页列表返回结果
 *
 * @author: zzs
 * @date: 2019/3/27
 */
data class HomeListResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: Data
)