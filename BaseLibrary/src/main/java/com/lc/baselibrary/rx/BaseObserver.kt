package com.lc.baselibrary.rx

import com.lc.baselibrary.data.model.BaseResp
import com.lc.baselibrary.data.net.HttpStatus
import com.lc.baselibrary.data.net.exception.ExceptionHandle
import com.lc.baselibrary.mvp.BaseContract
import io.reactivex.observers.ResourceObserver

/**
 * Created by LC on 2019/5/16
 */
abstract class BaseObserver<T:BaseResp<*>> : ResourceObserver<T> {

    private var mView: BaseContract.IBaseView? = null
    private var mErrorMsg = ""
    private var bShowLoading = true

    constructor(view: BaseContract.IBaseView) {
        this.mView = view
    }

    constructor(view: BaseContract.IBaseView, bShowLoading: Boolean) {
        this.mView = view
        this.bShowLoading = bShowLoading
    }

    /**
     * 成功的回调
     */
    protected abstract fun onSuccess(t: T)

    /**
     * 错误的回调
     */
    protected fun onError(t: T) {}

    override fun onStart() {
        super.onStart()
        if (bShowLoading) mView?.showLoading()
//        if (!NetWorkUtil.isConnected()) {
//            mView?.showDefaultMsg("当前网络不可用，请检查网络设置")
//            onComplete()
//        }
    }

    override fun onNext(t: T) {
        mView?.hideLoading()
        when {
            t.code == HttpStatus.SUCCESS -> onSuccess(t)
            t.code == HttpStatus.TOKEN_INVALID -> {
                // TODO Token 过期，重新登录
            }
            else -> {
                onError(t)
                if (t.message.isNotEmpty())
                    mView?.showDefaultMsg(t.message)
            }
        }
    }

    override fun onError(e: Throwable) {
        mView?.hideLoading()
        if (mView == null) {
            throw RuntimeException("mView can not be null")
        }
        if (mErrorMsg.isEmpty()) {
            mErrorMsg = ExceptionHandle.handleException(e)
        }
        mView?.showDefaultMsg(mErrorMsg)
    }

    override fun onComplete() {
        mView?.hideLoading()
    }
}