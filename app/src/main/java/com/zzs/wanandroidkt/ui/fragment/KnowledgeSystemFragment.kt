package com.zzs.wanandroidkt.ui.fragment

import android.support.v7.widget.GridLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.adapter.KnowledgeSystemAdapter
import com.zzs.wanandroidkt.base.BaseFragment
import com.zzs.wanandroidkt.bean.KnowSection
import com.zzs.wanandroidkt.bean.KnowledgeSystemResponse
import com.zzs.wanandroidkt.loge
import com.zzs.wanandroidkt.presenter.KnowledgeSystemPresenterImpl
import com.zzs.wanandroidkt.toast
import com.zzs.wanandroidkt.view.KnowledgeSystemView
import kotlinx.android.synthetic.main.frg_knowledge_system.*

/**
 * 知识体系
 *
 * @author: zzs
 * @date: 2019/11/17
 */
class KnowledgeSystemFragment : BaseFragment(), KnowledgeSystemView, OnRefreshListener {
    companion object {
        const val TAG = "KnowledgeSystemFragment"
        public fun newInstance(): KnowledgeSystemFragment {
            return KnowledgeSystemFragment()
        }
    }

    private val mAdapter by lazy {
        KnowledgeSystemAdapter(mutableListOf<KnowSection>())
    }

    private val mKnowledgePresenter: KnowledgeSystemPresenterImpl by lazy {
        KnowledgeSystemPresenterImpl(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mKnowledgePresenter.getKnowledgeSystemList()
    }

    override fun getKnowledgeSystemList(knowledgeResponse: KnowledgeSystemResponse) {
        val data = knowledgeResponse.data
        val mutableListOf = mutableListOf<KnowSection>()
        for (bean in data) {
            val knowSection = KnowSection(true, bean.name)
            mutableListOf.add(knowSection)
            val children = bean.children
            for (child in children) {
                val knowSection1 = KnowSection(child)
                mutableListOf.add(knowSection1)
            }
        }
        mAdapter.setNewData(mutableListOf)
        refresh_layout.finishRefresh()
    }

    override fun getKnowledgeSystemFail(errorMessage: String) {
        activity?.toast(errorMessage)
        refresh_layout.finishRefresh()
        loge(TAG, errorMessage)
    }

    override fun cancelRequest() {
    }

    override fun getLayoutId(): Int {
        return R.layout.frg_knowledge_system
    }

    override fun initData() {
    }

    override fun initView() {
        initRecycler()
    }

    private fun initRecycler() {
        refresh_layout.setOnRefreshListener(this)
        refresh_layout.autoRefresh()
        recyclerView.run {
            layoutManager = GridLayoutManager(activity, 4)
            adapter = mAdapter
        }
    }
}