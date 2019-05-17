package com.lc.usercenter.contract

import com.lc.baselibrary.data.model.BaseResp
import com.lc.baselibrary.mvp.BaseContract
import io.reactivex.Observable

/**
 * Created by LC on 2019/5/16
 */
interface RegisterContract {
    interface IRegisterModel : BaseContract.IBaseModel {
        //用户注册
        fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>>
    }

    interface IRegisterView : BaseContract.IBaseView {

        fun registerResult(boolean: Boolean)
    }

    interface IRegisterPresenter : BaseContract.IBasePresenter<IRegisterView> {

        fun register(mobile: String, pwd: String, verifyCode: String)
    }
}