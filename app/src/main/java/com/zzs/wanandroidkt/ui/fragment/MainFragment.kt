package com.zzs.wanandroidkt.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.AppCompatButton
import android.widget.TextView
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseFragment
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_main.*
import me.yokeyword.fragmentation.SupportFragment

/**
 * @author: zzs
 * @date: 2019/3/17
 */
class MainFragment : BaseFragment() {

    val FIRST = 0
    val SECOND = 1
    val THIRD = 2
    private val mFragments = arrayOfNulls<SupportFragment>(3)
    override fun initData() {
    }

    private lateinit var navigationViewUsername: TextView

    private lateinit var navigationViewLogout: AppCompatButton

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private val username: String by Preference(Constant.USERNAME_KEY, "")


    companion object {
        fun newInstance(): MainFragment {
            val bundle = Bundle()
            val mainFragment = MainFragment()
            mainFragment.arguments = bundle
            return mainFragment
        }
    }

    override fun cancelRequest() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val firstFragment = findChildFragment(HomeFragment::class.java)
        if (firstFragment == null) {
            mFragments[FIRST] = HomeFragment.newInstance()
            mFragments[SECOND] = KnowledgeSystemFragment.newInstance()
            mFragments[THIRD] = HomeFragment.newInstance()

            loadMultipleRootFragment(
                R.id.content, FIRST,
                mFragments[FIRST],
                mFragments[SECOND],
                mFragments[THIRD]
            )
        } else {
            mFragments[FIRST] = firstFragment
            mFragments[SECOND] = findChildFragment(HomeFragment::class.java)
            mFragments[THIRD] = findChildFragment(HomeFragment::class.java)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {

        toolbar.run {
            title = getString(R.string.app_name)
        }

        bottomNavigation.run {
            this.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }

        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                activity, this,
                toolbar
                , R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

        navigationView.run {
            setNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.nav_like ->
                        true
                    R.id.nav_about ->
                        true
                    else ->
                        true
                }
            }
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navigationViewUsername = navigationView.getHeaderView(0)
            .findViewById(R.id.navigationViewUsername)
        navigationViewLogout = navigationView.getHeaderView(0)
            .findViewById(R.id.navigationViewLogout)
        navigationViewUsername.run {
            if (!isLogin) {
                text = "还没有登录"
            } else {
                text = username
            }
        }

        navigationViewLogout.run {
            text = if (!isLogin) {
                "点击登录"
            } else {
                "退出"
            }

            setOnClickListener {
                if (!isLogin) {
                    Intent(activity, LoginActivity::class.java).run {
                        startActivityForResult(this, Constant.MAIN_REQUEST_CODE)
                    }
                } else {
                    Preference.clear()
                    navigationViewUsername.text = "退出登录成功"
                }
            }
        }
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showHideFragment(mFragments[FIRST])
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_knowledge_system -> {
                    showHideFragment(mFragments[SECOND])
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constant.MAIN_REQUEST_CODE -> {
                if (resultCode == RESULT_OK) {
                    navigationViewUsername.text = data?.getStringExtra(Constant.CONTENT_TITLE_KEY)
                    navigationViewLogout.text = "退出登录"
                }
            }
        }
    }

}