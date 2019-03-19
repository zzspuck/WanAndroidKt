package com.zzs.wanandroidkt.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.gyf.barlibrary.ImmersionBar
import me.yokeyword.fragmentation.SupportActivity

/**
 * BaseActivity
 *
 * @author: zzs
 * @date: 2019/3/5
 */
abstract class BaseActivity : SupportActivity() {
    protected lateinit var immersionBar: ImmersionBar

    private val imm: InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initImmersionBar()
        initData()
        initView()
    }

    abstract fun getLayoutId(): Int

    protected open fun initImmersionBar() {
        immersionBar = ImmersionBar.with(this)
        immersionBar.init()
    }

    protected abstract fun cancelRequest()

    override fun onDestroy() {
        super.onDestroy()
        immersionBar.destroy()
        cancelRequest()
    }

    override fun finish() {
        if (!isFinishing) {
            super.finish()
            hideSoftKeyBoard()
        }
    }

    private fun hideSoftKeyBoard() {
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 2) }
    }

    open fun initData(){}

    open fun initView(){}
}