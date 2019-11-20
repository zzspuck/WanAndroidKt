package com.zzs.wanandroidkt.presenter

import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse

/**
 * 知识体系Presenter
 *
 * @author: zzs
 * @date: 2019/11/20
 */
interface KnowledgeSystemPresenter {

    interface onKnowledgeSystemListener {

        fun getKnowledgeSystemSuccess(result: KnowledgeSystemResponse)

        fun getKnowledgeSystemFail(errorMessage: String)

        fun getKnowledgeSystemList()
    }
}