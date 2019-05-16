package com.lc.baselibrary.view.activity

import com.lc.baselibrary.presenter.BasePresenter
import com.lc.baselibrary.presenter.view.BaseView

/**
 * Created by LC on 2019/5/15
 */
open class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mPresenter: T

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}