package com.lc.baselibrary.presenter

import com.lc.baselibrary.presenter.view.BaseView

/**
 * Created by LC on 2019/5/15
 */
open class BasePresenter<T: BaseView>{
    lateinit var mView:T

}