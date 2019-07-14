package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.ArticleListResponse
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.model.CollecArticleModel
import com.zzs.wanandroidkt.model.CollectArticleModelImpl
import com.zzs.wanandroidkt.model.TypeArticleModel
import com.zzs.wanandroidkt.model.TypeArticleModelImpl
import com.zzs.wanandroidkt.view.CollectArticleView
import com.zzs.wanandroidkt.view.TypeArticleView

/**
 * 对应文章类型列表Presenter实现类
 *
 * @author: zzs
 * @date: 2019/7/13
 */
class TypeArticlePresenterImpl(
    private val typeArticleView: TypeArticleView,
    private val collectView: CollectArticleView
) : TypeArticlePresenter, TypeArticlePresenter.onTypeArticleListListener, HomePresenter.OnCollectArticleListener {


    /**
     * 类型文章Model实现类
     */
    private val mTypeArticleModel: TypeArticleModel = TypeArticleModelImpl()

    /**
     * 收藏文章Model
     */
    private val mCollectionArticleModel: CollecArticleModel = CollectArticleModelImpl()

    /**
     * 获取对应文章列表
     */
    override fun getTypeArticleList(page: Int, cid: Int) {
        mTypeArticleModel.getTypeArticleList(this, page, cid)
    }

    /**
     * 获取成功
     */
    override fun getTypeArticeleListSuc(result: ArticleListResponse) {
        if (result.errorCode != 0) {
            typeArticleView.getTypeArticleListFail(result.errorMsg)
            return
        }
        typeArticleView.getTypeArticeleListSuc(result)
    }

    /**
     * 获取失败
     */
    override fun getTypeArticleListFail(errorMessage: String?) {
        typeArticleView.getTypeArticleListFail(errorMessage)
    }

    /**
     * 收藏文章
     */
    override fun collectArticle(id: Int, isAdd: Boolean) {
        mCollectionArticleModel.collectArticel(this, id, isAdd)
    }

    /**
     * 收藏成功
     */
    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        if (result.errorCode != 0) {
            collectView.collectArticleFailed(result.errorMsg, isAdd)
        }

        collectView.collectArticleSuccess(result, isAdd)
    }

    /**
     * 收藏失败
     */
    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        collectView.collectArticleFailed(errorMessage, isAdd)
    }

    /**
     * 取消网络请求
     */
    fun cancelRequest() {
        mTypeArticleModel.cancelRequest()
        mCollectionArticleModel.cancelCollectRequest()
    }
}