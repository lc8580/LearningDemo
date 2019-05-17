package com.lc.baselibrary.ext

import com.lc.baselibrary.data.model.BaseResp
import com.lc.baselibrary.data.net.HttpStatus
import com.lc.baselibrary.data.net.exception.ExceptionHandle
import com.lc.baselibrary.data.net.function.RetryWithDelay
import com.lc.baselibrary.mvp.BaseContract
import com.lc.baselibrary.rx.SchedulerUtils
import com.lc.baselibrary.utils.NetWorkUtil
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by LC on 2019/5/17
 */
fun <T : BaseResp<*>> Observable<T>.excute(
        model: BaseContract.IBaseModel?,
        view: BaseContract.IBaseView?,
        isShowLoading: Boolean = true,
        onSuccess: (T) -> Unit,
        onError: ((T) -> Unit)? = null
) {
    this.compose(SchedulerUtils.ioToMain()).retryWhen(RetryWithDelay()).subscribe(object : Observer<T>{
        override fun onComplete() {
            view?.hideLoading()
        }

        override fun onSubscribe(d: Disposable) {
            if (isShowLoading) view?.showLoading()
            model?.addDisposable(d)
            if (!NetWorkUtil.isConnected()) {
                view?.showDefaultMsg("当前网络不可用，请检查网络设置")
                d.dispose()
                onComplete()
            }
        }

        override fun onNext(t: T) {
            view?.hideLoading()
            when {
                t.code == HttpStatus.SUCCESS -> onSuccess.invoke(t)
                t.code == HttpStatus.TOKEN_INVALID -> {
                    // Token 过期，重新登录
                }
                else -> {
                    if (onError != null) {
                        onError.invoke(t)
                    } else {
                        if (t.message.isNotEmpty())
                            view?.showDefaultMsg(t.message)
                    }
                }
            }
        }

        override fun onError(e: Throwable) {
            view?.hideLoading()
            view?.showError(ExceptionHandle.handleException(e))
        }
    })
}