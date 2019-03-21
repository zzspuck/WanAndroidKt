package com.zzs.wanandroidkt.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * @author: zzs
 * @date: 2019/3/17
 */
class MainFragment : BaseFragment() {

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
            setOnNavigationItemReselectedListener {
                object : BottomNavigationView.OnNavigationItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {
                        return when (item.itemId) {
                            R.id.navigation_home -> {
                                true
                            }
                            R.id.navigation_type -> {
                                true

                            }
                            else -> false
                        }
                    }

                }
            }
        }

        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                activity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }

        navigationView.run {
            this.setNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
    }

    val onNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
       when(item.itemId) {
           R.id.nav_like->
               return@OnNavigationItemSelectedListener true
           R.id.nav_about->
               return@OnNavigationItemSelectedListener true
       }
        drawerLayout.closeDrawer(GravityCompat.START)
        true
    }
}