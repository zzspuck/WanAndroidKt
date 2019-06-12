package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.model.CollecArticleModel
import com.zzs.wanandroidkt.model.CollectArticleModelImpl
import com.zzs.wanandroidkt.view.CollectArticleView

/**
 * 文章详情页的Presenter实现类
 *
 * @author: zzs
 * @date: 2019/6/13
 */
class ContentPresenterImpl(private val collectArticleView: CollectArticleView) :
    HomePresenter.OnCollectArticleListener {

    private val collectArticleModel: CollecArticleModel = CollectArticleModelImpl()

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectArticleView.collectArticleFailed(result.errorMsg, isAdd)
        } else {
            collectArticleView.collectArticleSuccess(result, isAdd)
        }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectArticleView.collectArticleFailed(errorMessage, isAdd)
    }

    override fun collectArticle(id: Int, isAdd: Boolean) {
        collectArticleModel.collectArticel(this, id, isAdd)

    }

    fun cancelRequest() {
        collectArticleModel.cancelCollectRequest()
    }
}