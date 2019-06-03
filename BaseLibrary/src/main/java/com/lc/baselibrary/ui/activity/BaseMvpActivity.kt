package com.lc.baselibrary.ui.activity

import android.os.Bundle
import com.lc.baselibrary.mvp.BaseContract
import com.lc.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast


/**
 * Created by LC on 2019/5/15
 */
abstract class BaseMvpActivity<P : BaseContract.IBasePresenter<*>> : BaseActivity(), BaseContract.IBaseView {

    protected var mPresenter: P? = null
    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始加载框
        mLoadingDialog = ProgressLoading.create(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
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