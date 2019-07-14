package com.zzs.wanandroidkt.ui.fragment

import android.content.Intent
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
import com.zzs.wanandroidkt.presenter.ContentPresenterImpl
import com.zzs.wanandroidkt.presenter.HomeFragmentPresenterImpl
import com.zzs.wanandroidkt.toast
import com.zzs.wanandroidkt.ui.activity.ContentActivity
import com.zzs.wanandroidkt.ui.activity.LoginActivity
import com.zzs.wanandroidkt.ui.activity.TypeContentActivity
import com.zzs.wanandroidkt.view.CollectArticleView
import com.zzs.wanandroidkt.view.HomeFragmentView
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 主页
 *
 * @author: zzs
 * @date: 2019/4/3
 */
class HomeFragment : BaseFragment(), HomeFragmentView, CollectArticleView, OnRefreshListener, OnLoadMoreListener {

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        val message: String? = if (isAdd) activity?.getString(R.string.bookmark_success) else activity?.getString(
            R.string.bookmark_cancel_success
        )
        message?.let { activity?.toast(it) }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        activity?.toast("收藏失败")
    }

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
        HomeFragmentPresenterImpl(this, this)
    }

    /**
     * presenter
     */
    private val collectArticlePresenter: ContentPresenterImpl by lazy {
        ContentPresenterImpl(this)
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
        if (page == 0) {
            refresh_layout.finishRefresh(false)
        } else {
            refresh_layout.finishLoadMore(false)
        }
        errorMessage?.let {
            activity?.toast(it)
        } ?: let {
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
        homeFragmentPresenter.cancelRequest()
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
        if (activity == null) {
            return@OnItemClickListener
        }
        if (datas.size != 0) {
            // 点击进入详情
            if (datas.size != 0) {
                val url = datas[position].link
                val id = datas[position].id
                val title = datas[position].title
                context?.let {
                    ContentActivity.openActiviey(context!!, url, id, title)
                }
            }
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
                        activity?.let {
                            data.chapterName?.let { it1 -> TypeContentActivity.openActivity(it, data.chapterId, it1) }
                        }

                    }
                    R.id.homeItemLike -> {
                        if (isLogin) {
                            val collect = data.collect
                            data.collect = !collect
                            homeAdapter.setData(position, data)
                            collectArticlePresenter.collectArticle(data.id, !collect)
                        } else {
                            activity?.let {
                                Intent(it, LoginActivity::class.java).run {
                                    startActivity(this)
                                }
                            }
                        }
                    }
                }
            }
        }
}