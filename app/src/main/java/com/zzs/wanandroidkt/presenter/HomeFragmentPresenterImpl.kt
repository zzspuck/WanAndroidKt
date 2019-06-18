package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.model.CollecArticleModel
import com.zzs.wanandroidkt.model.CollectArticleModelImpl
import com.zzs.wanandroidkt.model.HomeModel
import com.zzs.wanandroidkt.model.HomeModelImpl
import com.zzs.wanandroidkt.view.CollectArticleView
import com.zzs.wanandroidkt.view.HomeFragmentView

/**
 * @author: zzs
 * @date: 2019/4/3
 */
class HomeFragmentPresenterImpl(
    private val homeFragmentView: HomeFragmentView,
    private val collectionArticleView: CollectArticleView
) : HomePresenter.onHomeListListener, HomePresenter.OnCollectArticleListener {
    override fun collectArticle(id: Int, isAdd: Boolean) {
        collectArticleModel.collectArticel(this, id, isAdd)
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectionArticleView.collectArticleFailed(result.errorMsg, isAdd)
        } else {
            collectionArticleView.collectArticleSuccess(result, isAdd)
        }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectionArticleView.collectArticleFailed(errorMessage, isAdd)
    }

    private val homeModel: HomeModel = HomeModelImpl()
    private val collectArticleModel: CollecArticleModel = CollectArticleModelImpl()
    override fun getHomeList(page: Int) {
        homeModel.getHomeList(this, page)
    }

    override fun getHomeListSuccess(result: HomeListResponse) {

        if (result.errorCode != 0) {
            homeFragmentView.getHomeListFailed(result.errorMsg)
            return
        }
        // 列表总数
        val total = result.data.total
        if (total == 0) {
            homeFragmentView.getHomListZero()
            return
        }
        // 当第一页小鱼一页总数时
        if (total < result.data.size) {
            homeFragmentView.getHomeListSmall(result)
            return
        }

        homeFragmentView.getHomeListSuccess(result)
    }

    override fun getHomeListFailed(errorMessage: String?) {
        homeFragmentView.getHomeListFailed(errorMessage)
    }

    fun cancelRequest() {
        homeModel.cancelHomeListRequest()
    }
}