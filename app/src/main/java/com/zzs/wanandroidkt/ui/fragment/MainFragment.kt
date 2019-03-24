package com.zzs.wanandroidkt.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.AppCompatButton
import android.view.MenuItem
import android.widget.TextView
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseFragment
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.toast
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author: zzs
 * @date: 2019/3/17
 */
class MainFragment : BaseFragment() {
    private lateinit var navigationViewUsername: TextView

    private lateinit var navigationViewLogout: AppCompatButton

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    private val username:String by Preference(Constant.USERNAME_KEY, "")


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

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        super.initView()

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
            setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.nav_like ->
                            true
                        R.id.nav_about ->
                            true
                        else ->
                            true
                    }
                }
            })
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        navigationViewUsername = navigationView.getHeaderView(0)
            .findViewById(R.id.navigationViewUsername)
        navigationViewLogout = navigationView.getHeaderView(0)
            .findViewById(R.id.navigationViewLogout)
        navigationViewUsername.run {
            if (!isLogin) {
                text = "还没有登录"
            } else{
                text = username
            }
        }

        navigationViewLogout.run {
            text = if (!isLogin) {
                "点击登录"
            }else{
                "退出"
            }

            setOnClickListener {
                if (!isLogin) {
                    activity?.toast("请填写登录界面")
                }  else{
                    Preference.clear()
                    navigationViewUsername.text = "退出登录成功"
                }
            }
        }
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home ->
                    return@OnNavigationItemSelectedListener true
                R.id.navigation_type ->
                    return@OnNavigationItemSelectedListener true
            }
            false
        }

}