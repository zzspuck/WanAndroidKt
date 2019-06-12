package com.zzs.wanandroidkt.view

import com.zzs.wanandroidkt.bean.HomeListResponse

/**
 * 收藏view
 *
 * @author: zzs
 * @date: 2019/6/12
 */
interface CollectArticleView{
    fun collectArticleSuccess(result: HomeListResponse,isAdd:Boolean)

    fun collectArticleFailed(errorMessage:String?, isAdd: Boolean)
}