package com.zzs.wanandroidkt.bean

/**
 * @author: zzs
 * @date: 2019/5/16
 */
data class LoginResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: Data
) {
    data class Data(
        var id: Int,
        var username: String,
        var password: String,
        var icon: String?,
        var type: Int,
        var collectIds: List<Int>?
    )
}