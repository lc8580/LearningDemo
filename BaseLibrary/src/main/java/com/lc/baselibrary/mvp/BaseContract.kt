package com.lc.baselibrary.mvp

import io.reactivex.disposables.Disposable

/**
 * Created by LC on 2019/5/16
 */
interface BaseContract {
    interface IBasePresenter< T: IBaseView>{
        /**
         * 绑定 View
         */
        fun attachView(mView: T)

        /**
         * 解绑 View
         */
        fun detachView()
    }

    interface IBaseView {
        /**
         * 显示加载
         */
        fun showLoading()

        /**
         * 隐藏加载
         */
        fun hideLoading()

        /**
         * 使用默认的样式显示信息: CustomToast
         */
        fun showDefaultMsg(msg: String)

        /**
         * 显示信息
         */
        fun showMsg(msg: String)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg: String)
    }

    interface IBaseModel {

        fun addDisposable(disposable: Disposable?)

        fun onDetach()

    }
}