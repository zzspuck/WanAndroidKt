package com.zzs.wanandroidkt.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.bean.Datas

/**
 * 类型文章列表适配器
 *
 * @author: zzs
 * @date: 2019/7/14
 */
class TypeArticleAdapter(data: MutableList<Datas>?) :
    BaseQuickAdapter<Datas, BaseViewHolder>(R.layout.home_list_item, data) {
    override fun convert(helper: BaseViewHolder, item: Datas?) {
        item ?: return
        helper.setText(R.id.homeItemTitle, item.title)
            .setText(R.id.homeItemAuthor, item.author)
            .setText(R.id.homeItemType, item.chapterName)
            .addOnClickListener(R.id.homeItemType)
            .setTextColor(R.id.homeItemType, mContext.resources.getColor(R.color.colorPrimary))
            .linkify(R.id.homeItemType)
            .setText(R.id.homeItemDate, item.niceDate)
            .setImageResource(
                R.id.homeItemLike,
                if (item.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like
            )
            .addOnClickListener(R.id.homeItemLike)
    }
}