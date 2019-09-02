package com.lc.usercenter.presenter

import com.lc.baselibrary.ext.excute
import com.lc.baselibrary.mvp.BasePresenter
import com.lc.usercenter.contract.LoginContract
import com.lc.usercenter.impl.LoginModelImpl

/*
    用户注册Presenter
 */
class LoginPresenter : BasePresenter<LoginContract.ILoginModel, LoginContract.ILoginView>(), LoginContract.ILoginPresenter {


    override fun createModel(): LoginContract.ILoginModel? = LoginModelImpl()

    override fun login(mobile: String, pwd: String, pushId: String) {
        mModel?.login(mobile, pwd, pushId)?.excute(mModel, mView,
                onSuccess = {
                    mView?.loginResult(it.data)
        })
    }


}
