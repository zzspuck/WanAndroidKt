package com.zzs.wanandroidkt.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyf.barlibrary.ImmersionBar
import me.yokeyword.fragmentation.SupportFragment


/**
 * BaseFragment
 *
 * @author: zzs
 * @date: 2019/3/7
 */
abstract class BaseFragment : SupportFragment() {
    protected var mActivity: Activity? = null

    protected var mRootView: View? = null
    protected abstract fun cancelRequest()

    override fun onDestroyView() {
        super.onDestroyView()
        cancelRequest()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as Activity?
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }

    public fun initImmersionBar() {
        ImmersionBar.with(this).keyboardEnable(true).init()
    }

    private fun isImmersionBarEnabled(): Boolean {
        return true
    }

    protected abstract fun getLayoutId(): Int

    protected fun initData() {}

    protected fun initView() {}

    override fun onSupportVisible() {
        super.onSupportVisible()
        if (isImmersionBarEnabled()) {
            initImmersionBar()
        }
    }

    protected fun setTitleBar(): Int {
        //return R.id.toolbar
        return 0
    }

    protected fun setStatusBarView(): Int {
        return 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleBar = view.findViewById<View>(setTitleBar())
        if (titleBar != null) {
            ImmersionBar.setTitleBar(mActivity, titleBar)
        }
        val statusBarView = view.findViewById<View>(setStatusBarView())
        if (statusBarView != null) {
            ImmersionBar.setStatusBarView(mActivity, statusBarView)
        }
        initData()
        initView()
    }
}