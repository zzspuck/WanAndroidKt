package com.zzs.wanandroidkt.bean

/**
 * 知识体系结果类
 *
 * @author: zzs
 * @date: 2019/11/19
 */
data class KnowledgeSystemResponse(
    val `data`: List<KnowData>,
    val errorCode: Int,
    val errorMsg: String
) {

  public  data class KnowData(
        val children: List<KnowData>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
    )

}
