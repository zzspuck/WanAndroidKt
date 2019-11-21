package com.zzs.wanandroidkt.bean

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @author: zzs
 * @date: 2019/11/21
 */
class KnowSection : SectionEntity<KnowledgeSystemResponse.KnowData> {

    constructor(isHeader: Boolean, header: String) : super(isHeader, header) {
    }

    constructor(t: KnowledgeSystemResponse.KnowData) : super(t) {}
}