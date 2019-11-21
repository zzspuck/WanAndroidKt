package com.zzs.wanandroidkt.adapter

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.bean.KnowSection
import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse

/**
 * @author: zzs
 * @date: 2019/11/21
 */
class KnowledgeSystemAdapter(data: MutableList<KnowSection>) :
    BaseSectionQuickAdapter<KnowSection, BaseViewHolder>(
        R.layout.item_section_content,
        R.layout.item_know_title, data
    ) {

    override fun convertHead(helper: BaseViewHolder, item: KnowSection?) {
        item?.run {
            helper.setText(R.id.tv_title, header)
        }
    }

    override fun convert(helper: BaseViewHolder, item: KnowSection?) {
        item?.run {
            val knowData = t as KnowledgeSystemResponse.KnowData
            helper.setText(R.id.tv_content, knowData.name)
        }
    }
}