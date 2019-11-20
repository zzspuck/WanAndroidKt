package com.zzs.wanandroidkt.view

import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse

/**
 * 知识体系view
 *
 * @author: zzs
 * @date: 2019/11/20
 */
interface KnowledgeSystemView {

    /**
     * 获取知识体系列表数据
     *
     * @param knowledgeResponse 数据
     */
    fun getKnowledgeSystemList(knowledgeResponse: KnowledgeSystemResponse)

    fun getKnowledgeSystemFail(errorMessage: String)
}