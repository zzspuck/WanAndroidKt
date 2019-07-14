package com.zzs.wanandroidkt.model

import RetrofitHelper
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.bean.ArticleListResponse
import com.zzs.wanandroidkt.cancelByActivity
import com.zzs.wanandroidkt.presenter.TypeArticlePresenter
import com.zzs.wanandroidkt.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 类型文章Model实现类型
 *
 * @author: zzs
 * @date: 2019/7/13
 */
class TypeArticleModelImpl : TypeArticleModel {
    private var articleList: Deferred<ArticleListResponse>? = null
    override fun getTypeArticleList(
        onTypeArticleListListener: TypeArticlePresenter.onTypeArticleListListener,
        page: Int,
        cid: Int
    ) {

        CoroutineScope(Dispatchers.Main)
            .launch {
                tryCatch({
                    onTypeArticleListListener.getTypeArticleListFail(it.message)
                }, {
                    articleList?.cancelByActivity()
                    articleList = RetrofitHelper.retrofitService.getArticleList(page, cid)
                    val await = articleList?.await()
                    await ?: let {
                        onTypeArticleListListener.getTypeArticleListFail(Constant.RESULT_NULL)
                        return@launch
                    }

                    onTypeArticleListListener.getTypeArticeleListSuc(await)
                })
            }
    }

    override fun cancelRequest() {
        articleList?.cancelByActivity()
    }
}