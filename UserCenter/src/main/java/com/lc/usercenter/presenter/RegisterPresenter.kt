package com.lc.usercenter.presenter

import com.lc.baselibrary.ext.excute
import com.lc.baselibrary.mvp.BasePresenter
import com.lc.usercenter.contract.RegisterContract
import com.lc.usercenter.impl.RegistModelImpl

/*
    用户注册Presenter
 */
class RegisterPresenter : BasePresenter<RegisterContract.IRegisterModel,RegisterContract.IRegisterView>(), RegisterContract.IRegisterPresenter {

    private val registModelImpl = RegistModelImpl()
    override fun register(mobile: String, pwd: String, verifyCode: String) {

        registModelImpl.register("", "", "").excute(registModelImpl, mView, onSuccess = {
            mView?.registerResult(true)
        })
    }

}
