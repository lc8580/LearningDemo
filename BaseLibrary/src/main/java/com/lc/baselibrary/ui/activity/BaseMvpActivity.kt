package com.lc.baselibrary.ui.activity

import com.lc.baselibrary.mvp.BaseContract
import org.jetbrains.anko.toast

/**
 * Created by LC on 2019/5/15
 */
open class BaseMvpActivity<P : BaseContract.IBasePresenter<*>> : BaseActivity(), BaseContract.IBaseView {


    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMsg: String) {
        toast(errorMsg)
    }

    override fun showDefaultMsg(msg: String) {
        toast(msg)
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }
}