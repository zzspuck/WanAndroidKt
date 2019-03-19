package com.zzs.wanandroidkt.ui.activity

import android.os.Bundle
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseActivity
import com.zzs.wanandroidkt.ui.fragment.MainFragment
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun cancelRequest() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }
    }

    override fun onCreateFragmentAnimator(): DefaultHorizontalAnimator {
        return DefaultHorizontalAnimator()
    }
}
