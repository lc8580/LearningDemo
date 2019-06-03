package com.lc.usercenter.presenter

import com.lc.baselibrary.ext.excute
import com.lc.baselibrary.mvp.BasePresenter
import com.lc.usercenter.contract.RegisterContract
import com.lc.usercenter.impl.RegistModelImpl

/*
    用户注册Presenter
 */
class RegisterPresenter : BasePresenter<RegisterContract.IRegisterModel, RegisterContract.IRegisterView>(), RegisterContract.IRegisterPresenter {

    override fun createModel(): RegisterContract.IRegisterModel?= RegistModelImpl()

    override fun register(mobile: String, pwd: String, verifyCode: String) {

        mModel?.register(mobile, pwd, verifyCode)?.excute(mModel, mView,
                onSuccess = {
                    mView?.registerResult(true)
                },
                onError = {
                    mView?.registerResult(true)
                })
    }

}
