package com.zzs.wanandroidkt.model

import RetrofitHelper
import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse
import com.zzs.wanandroidkt.cancelByActivity
import com.zzs.wanandroidkt.presenter.KnowledgeSystemPresenter
import com.zzs.wanandroidkt.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 知识体系Model实现类
 *
 * @author: zzs
 * @date: 2019/11/20
 */
class KnowledgeSystemModelImpl : KnowledgeSystemModel {
    var knowDeffered: Deferred<KnowledgeSystemResponse>? = null
    override fun getKnowledgeSystemList(knowledgeSystemLister: KnowledgeSystemPresenter.onKnowledgeSystemListener) {
        CoroutineScope(Dispatchers.Main).launch {
            tryCatch({
                it.printStackTrace()
                knowledgeSystemLister.getKnowledgeSystemFail(it.toString())
            }, {
                knowDeffered?.cancelByActivity()
                knowDeffered = RetrofitHelper.retrofitService.getKnorledgeSystemLIst()
                val await = knowDeffered?.await()
                await ?: let {
                    knowledgeSystemLister.getKnowledgeSystemFail("数据为空")
                    return@launch
                }
                await?.let { knowledgeSystemLister.getKnowledgeSystemSuccess(it) }
            })
        }
    }

    override fun cancelKnowledgeSystemList() {
        knowDeffered?.cancelByActivity()
    }
}