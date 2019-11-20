package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse
import com.zzs.wanandroidkt.model.KnowledgeSystemModel
import com.zzs.wanandroidkt.model.KnowledgeSystemModelImpl
import com.zzs.wanandroidkt.view.KnowledgeSystemView

/**
 * 知识体系Presenter实现类
 *
 * @author: zzs
 * @date: 2019/11/20
 */
class KnowledgeSystemPresenterImpl(private val knowledgeSystemView: KnowledgeSystemView) :
    KnowledgeSystemPresenter.onKnowledgeSystemListener {

    /**
     * model
     */
    private val mKnowledgeModel: KnowledgeSystemModel = KnowledgeSystemModelImpl()

    override fun getKnowledgeSystemList() {
        mKnowledgeModel.getKnowledgeSystemList(this)
    }

    override fun getKnowledgeSystemSuccess(result: KnowledgeSystemResponse) {
        knowledgeSystemView.getKnowledgeSystemList(result)
    }

    override fun getKnowledgeSystemFail(errorMessage: String) {
        knowledgeSystemView.getKnowledgeSystemFail(errorMessage)
    }

}