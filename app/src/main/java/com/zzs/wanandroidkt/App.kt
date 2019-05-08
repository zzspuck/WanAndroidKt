package com.zzs.wanandroidkt

import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.zzs.wanandroidkt.base.Preference

/**
 * Application
 *
 * @author: zzs
 * @date: 2019/4/17
 */
class App : Application() {

    //static 代码段可以防止内存泄露
    companion object {
        private lateinit var mContext: Application

        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
                override fun createRefreshHeader(context: Context, layout: RefreshLayout): RefreshHeader {
                    // 全局设置主题颜色
                    layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)

                    return ClassicsHeader(context)
                }
            })

            SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator {
                override fun createRefreshFooter(context: Context, layout: RefreshLayout): RefreshFooter {
                    return ClassicsFooter(context).setDrawableSize(20f)
                }
            })
        }

        fun getContext(): Context {
            return mContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        Preference.setContext(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }
}