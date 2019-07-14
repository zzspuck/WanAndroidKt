package com.zzs.wanandroidkt.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.zzs.wanandroidkt.App
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.adapter.TypeArticleAdapter
import com.zzs.wanandroidkt.base.BaseActivity
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.bean.ArticleListResponse
import com.zzs.wanandroidkt.bean.Datas
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.presenter.TypeArticlePresenterImpl
import com.zzs.wanandroidkt.toast
import com.zzs.wanandroidkt.view.CollectArticleView
import com.zzs.wanandroidkt.view.TypeArticleView
import kotlinx.android.synthetic.main.activity_type_content.*

/**
 * 类型列表页面
 *
 * @author: zzs
 * @date: 2019/6/20
 */
class TypeContentActivity : BaseActivity(), TypeArticleView, CollectArticleView, OnRefreshListener, OnLoadMoreListener {

    override fun onRefresh(refreshLayout: RefreshLayout) {
        page = 0
        mTypeArticlePresenter.getTypeArticleList(page, mCid)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        page += 1
    }

    private lateinit var firstTitle: String
    private var mCid: Int = 0

    /**
     * 当前页数
     */
    private var page: Int = 0

    /**
     * check login for SharedPreferences
     */
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    /**
     * 适配器
     */
    private val mAdapter: TypeArticleAdapter by lazy {
        TypeArticleAdapter(mutableListOf<Datas>())
    }

    /**
     * 文章类型Presenter
     */
    private val mTypeArticlePresenter: TypeArticlePresenterImpl by lazy {
        TypeArticlePresenterImpl(this, this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_type_content
    }

    override fun cancelRequest() {

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.typeSecondToolbar).init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        typeSecondToolbar.run {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        intent.extras?.let {
            it.getString(Constant.CONTENT_TITLE_KEY)?.let {
                firstTitle = it
                typeSecondToolbar.title = it
            }
            mCid = it.getInt(Constant.CONTENT_CID_KEY, 0)
        }
        recyclerView.run {

            layoutManager = LinearLayoutManager(App.getContext())
            adapter = mAdapter
        }
        refresh_layout.run {
            setOnRefreshListener(this@TypeContentActivity)
            setOnLoadMoreListener(this@TypeContentActivity)
            autoRefresh()
        }

        setClickListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setClickListener() {
        mAdapter.run {
            setOnItemChildClickListener(object : BaseQuickAdapter.OnItemChildClickListener {
                override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View, position: Int) {
                    val data = mAdapter.data[position]
                    when (view.id) {
                        R.id.homeItemLike -> {
                            if (isLogin) {
                                val collect = data.collect
                                data.collect = !collect
                                mAdapter.setData(position, data)
                                mTypeArticlePresenter.collectArticle(data.id, !collect)
                            } else {
                                Intent(this@TypeContentActivity, LoginActivity::class.java).run {
                                    startActivityForResult(this, Constant.MAIN_REQUEST_CODE)
                                }
                                toast(getString(R.string.login_please_login))
                            }
                        }
                    }
                }
            })
            setOnItemClickListener(object : BaseQuickAdapter.OnItemClickListener {
                override fun onItemClick(p0: BaseQuickAdapter<*, *>?, p1: View?, p2: Int) {
                    val data = mAdapter.data.get(p2)
                    ContentActivity.openActiviey(this@TypeContentActivity, data.link, data.id, data.title)
                }
            })
        }
    }

    override fun getTypeArticeleListSuc(result: ArticleListResponse) {
        val total = result.data.total
        mAdapter.run {
            if (result.data.offset >= total || result.data.size >= total) {
                refresh_layout.finishLoadMoreWithNoMoreData()
                return
            }
            if (page == 0) {
                replaceData(result.data.datas)
                refresh_layout.finishRefresh()
            } else {
                addData(result.data.datas)
                refresh_layout.finishLoadMore()
            }
        }

    }

    override fun getTypeArticleListFail(errorMessage: String?) {
        if (page == 0) {
            refresh_layout.finishRefresh(false)
        } else {
            refresh_layout.finishLoadMore(false)
        }
        errorMessage?.let {
            toast(it)
        } ?: let {
            toast("Failed to get the data")
        }
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
        val message: String? = if (isAdd) this.getString(R.string.bookmark_success) else getString(
            R.string.bookmark_cancel_success
        )
        message?.let { toast(it) }
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
        toast("收藏失败")
    }


    /**
     * 启动页面
     */
    companion object {
        fun openActivity(context: Context, cid: Int, title: String) {
            Intent(context, TypeContentActivity::class.java).run {
                putExtra(Constant.CONTENT_CID_KEY, cid)
                putExtra(Constant.CONTENT_TITLE_KEY, title)
                context.startActivity(this)
            }
        }
    }
}