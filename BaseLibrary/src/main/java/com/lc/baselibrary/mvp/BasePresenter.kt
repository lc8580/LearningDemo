package com.lc.baselibrary.mvp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 * Created by LC on 2019/5/15
 */
abstract class BasePresenter<M : BaseContract.IBaseModel, V : BaseContract.IBaseView> :BaseContract.IBasePresenter<V>, LifecycleObserver{
    protected var mModel: M? = null
    protected var mView: V? = null

    private val isViewAttached: Boolean
        get() = mView != null

    private var mCompositeDisposable: CompositeDisposable? = null

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false


    override fun attachView(mView: V) {
        this.mView = mView
        if (this.mView is LifecycleOwner) {
            (this.mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }

        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }

    }

    override fun detachView() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
        // 保证activity结束时取消所有正在执行的订阅
        unDispose()
        mModel?.onDetach()
        this.mModel = null
        this.mView = null
        this.mCompositeDisposable = null
    }


    open fun addDisposable(disposable: Disposable?) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        disposable?.let { mCompositeDisposable?.add(it) }
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()  // 保证Activity结束时取消
        mCompositeDisposable = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
//         detachView()
        owner.lifecycle.removeObserver(this)
    }

    open fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException internal constructor() :
            RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}