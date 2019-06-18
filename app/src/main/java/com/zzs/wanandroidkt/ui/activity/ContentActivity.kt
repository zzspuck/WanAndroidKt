package com.zzs.wanandroidkt.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.ChromeClientCallbackManager
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseActivity
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.getAgentWeb
import com.zzs.wanandroidkt.presenter.ContentPresenterImpl
import com.zzs.wanandroidkt.toast
import com.zzs.wanandroidkt.view.CollectArticleView
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.fragment_main.toolbar

/**
 * 内容详情页面
 *
 * @author: zzs
 * @date: 2019/6/11
 */
class ContentActivity : BaseActivity(), CollectArticleView {

    private lateinit var agentWeb: AgentWeb

    private lateinit var shareTitle: String
    private lateinit var shareUrl: String
    private var sharedId: Int = 0

    companion object {
        fun openActiviey(context: Context, url: String, id: Int, title: String) {
            Intent(context, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, url)
                putExtra(Constant.CONTENT_ID_KEY, id)
                putExtra(Constant.CONTENT_TITLE_KEY, title)
                context.startActivity(this)
            }
        }
    }

    /**
     * presenter
     */
    private val collectArticlePresenter: ContentPresenterImpl by lazy {
        ContentPresenterImpl(this)
    }

    /**
     * 是否登录
     */
    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)


    override fun getLayoutId(): Int {
        return R.layout.activity_content
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = "正在加载中"
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        intent.extras?.let {
            sharedId = it.getInt(Constant.CONTENT_ID_KEY, 0)
            shareUrl = it.getString(Constant.CONTENT_URL_KEY, "")
            shareTitle = it.getString(Constant.CONTENT_TITLE_KEY, "")
            agentWeb = shareUrl.getAgentWeb(
                this,
                webContent,
                LinearLayout.LayoutParams(-1, -1),
                receivedTitleCallback
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_content, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.menuLike -> {
                // login
                if (!isLogin) {
                    Intent(this, LoginActivity::class.java).run {
                        startActivity(this)
                    }
                    toast(getString(R.string.login_please_login))
                    return true
                }
                // Collection station article
                collectArticlePresenter.collectArticle(sharedId, true)
                return true
            }
            R.id.menuBrowser -> {
                Intent().run {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse(shareUrl)
                    startActivity(this)
                }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun cancelRequest() {
    }

    override fun collectArticleSuccess(result: HomeListResponse, isAdd: Boolean) {
    }

    override fun collectArticleFailed(errorMessage: String?, isAdd: Boolean) {
    }

    private val receivedTitleCallback =
        ChromeClientCallbackManager.ReceivedTitleCallback { view, title ->
            title?.let {
                toolbar.title = it
            }
        }
}
