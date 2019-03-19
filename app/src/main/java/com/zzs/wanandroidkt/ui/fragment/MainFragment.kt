package com.zzs.wanandroidkt.ui.fragment

import android.os.Bundle
import com.zzs.wanandroidkt.R
import com.zzs.wanandroidkt.base.BaseFragment

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

}