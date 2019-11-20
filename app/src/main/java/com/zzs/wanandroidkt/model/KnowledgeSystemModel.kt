package com.zzs.wanandroidkt.model

import com.zzs.wanandroidkt.presenter.KnowledgeSystemPresenter

/**
 * 知识体系Model接口
 *
 * @author: zzs
 * @date: 2019/11/20
 */
interface KnowledgeSystemModel {

    fun getKnowledgeSystemList(knowledgeSystemLister: KnowledgeSystemPresenter.onKnowledgeSystemListener)

    fun cancelKnowledgeSystemList()
}