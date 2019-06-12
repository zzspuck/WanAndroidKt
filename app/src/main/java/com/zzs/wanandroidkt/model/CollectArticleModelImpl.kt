package com.zzs.wanandroidkt.model

import RetrofitHelper
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.cancelByActivity
import com.zzs.wanandroidkt.presenter.HomePresenter
import com.zzs.wanandroidkt.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 收藏model实现类
 *
 * @author: zzs
 * @date: 2019/6/12
 */
class CollectArticleModelImpl : CollecArticleModel {

    private var collectArticleAsync: Deferred<HomeListResponse>? = null

    override fun collectArticel(
        onCollectArticleListener: HomePresenter.OnCollectArticleListener,
        id: Int,
        isAdd: Boolean
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            tryCatch({
                it.printStackTrace()
                onCollectArticleListener.collectArticleFailed(it.toString(), isAdd)
            }, {
                collectArticleAsync?.cancelByActivity()
                collectArticleAsync = if (isAdd) {
                    RetrofitHelper.retrofitService.addCollectArticle(id)
                } else {
                    RetrofitHelper.retrofitService.removeCollectArticle(id)
                }
                val result = collectArticleAsync?.await()
                result ?: let {
                    onCollectArticleListener.collectArticleFailed(Constant.RESULT_NULL, isAdd)

                    return@launch
                }
                onCollectArticleListener.collectArticleSuccess(result, isAdd)
            })
        }
    }

    override fun cancelCollectRequest() {
        collectArticleAsync?.cancelByActivity()
    }
}