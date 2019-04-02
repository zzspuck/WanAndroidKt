package com.zzs.wanandroidkt.ui.fragment

import android.view.View
import com.zzs.wanandroidkt.Constant.Constant
import com.zzs.wanandroidkt.base.BaseFragment
import com.zzs.wanandroidkt.base.Preference
import com.zzs.wanandroidkt.bean.Datas
import com.zzs.wanandroidkt.bean.HomeListResponse
import com.zzs.wanandroidkt.presenter.HomeFragmentPresenterImpl
import com.zzs.wanandroidkt.view.HomeFragmentView

/**
 * 主页
 *
 * @author: zzs
 * @date: 2019/4/3
 */
class HomeFragment:BaseFragment(),HomeFragmentView{

    companion object {
        private const val Bannner_timer = 5000L
    }

    private var mainView:View? = null

    private val datas = mutableListOf<Datas>()

    private val homeFragmentPresenter:HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this)
    }

    private val isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)

    override fun getHomeListSuccess(result: HomeListResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeListFailed(errorMessage: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomListZero() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getHomeListSmall(result: HomeListResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancelRequest() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}