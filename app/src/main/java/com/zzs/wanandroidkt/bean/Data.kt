package com.zzs.wanandroidkt.bean

/**
 * @author: zzs
 * @date: 2019/3/27
 */
data class Data(var offset: Int,
                var size: Int,
                var total: Int,
                var pageCount: Int,
                var curPage:Int,
                var over:Boolean,
                var datas:List<Datas>)