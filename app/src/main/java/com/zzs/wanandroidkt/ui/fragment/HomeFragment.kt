package com.zzs.wanandroidkt.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zzs.wanandroidkt.App
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.adapter.HomeAdapter
import com.zzs.wanandroidkt.base.BaseFragment
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.bean.Datas
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.presenter.HomeFragmentPresenterImpl
import com.zzs.wanandroidkt.toast
import com.zzs.wanandroidkt.view.HomeFragmentView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 主页
 *
 * @author: zzs
 * @date: 2019/4/3
 */
class HomeFragment : BaseFragment(), HomeFragmentView, OnRefreshListener, OnLoadMoreListener {
    /**
     * 当前页数
     */
    var page = 0

    override fun initData() {
    }

    override fun initView() {
        refresh_layout.run {
            setOnRefreshListener(this@HomeFragment)
            autoRefresh()
            setOnLoadMoreListener(this@HomeFragment)
        }
        recyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
        }

        homeAdapter.run {
            bindToRecyclerView(recyclerView)
            onItemClickListener = this@HomeFragment.onItemClickListener
            onItemChildClickListener = this@HomeFragment.onItemChildClickListener
        }
    }

    companion object {
        private const val Bannner_timer = 5000L
        fun newInstance(): HomeFragment {
            val bundle = Bundle()
            val homeFragment = HomeFragment()
            homeFragment.arguments = bundle
            return homeFragment
        }
    }

    private var mainView: View? = null

    private val datas = mutableListOf<Datas>()

    private val homeFragmentPresenter: HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this)
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(App.getContext(), datas)
    }

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    override fun getHomeListSuccess(result: HomeListResponse) {
        result.data.datas.let {
            homeAdapter.run {
                val total = result.data.total
                if (result.data.offset >= total || data.size > total) {
                    refresh_layout.finishLoadMoreWithNoMoreData()
                    return@let
                }
                if (page == 0) {
                    refresh_layout.finishRefresh();
                    replaceData(it)
                } else {
                    refresh_layout.finishLoadMore()
                    addData(it)
                }
            }
        }
    }

    override fun getHomeListFailed(errorMessage: String?) {
        if (page==0) {
            refresh_layout.finishRefresh(false)
        } else{
            refresh_layout.finishLoadMore(false)
        }
        errorMessage?.let {
            activity?.toast(it)
        }?:let {
            activity?.toast("Failed to get the data")
        }
    }

    override fun getHomListZero() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeListSmall(result: HomeListResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    /**
     * 下拉刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
        refreshData()
    }

    /**
     * 加载更多
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page = homeAdapter.data.size / 20 + 1
        homeFragmentPresenter.getHomeList(page)
    }

    /**
     * 刷新数据
     */
    private fun refreshData() {
        homeFragmentPresenter.getHomeList()
    }

    private val onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
        if (datas.size != 0) {
            // 点击进入详情
        }
    }

    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
            if (datas.size != 0) {
                val data = datas[position]
                when (view.id) {
                    R.id.homeItemType -> {
                        data.chapterName ?: let {
                            activity?.toast("Category is empty")
                            return@OnItemChildClickListener
                        }
                        // 跳转 文章类型页面
                        activity?.toast("跳转文章类型页面")

                    }
                    R.id.homeItemLike -> {
                        if (isLogin) {
                            activity?.toast("调用收藏接口")
                        } else {
                            activity?.toast("跳转登录页面")
                        }
                    }
                }
            }
        }
}