package com.lc.usercenter.contract

import com.lc.baselibrary.data.model.BaseResp
import com.lc.baselibrary.mvp.BaseContract
import com.lc.usercenter.data.model.UserInfo
import io.reactivex.Observable

/**
 * Created by LC on 2019/5/16
 */
interface LoginContract {
    interface ILoginModel : BaseContract.IBaseModel {
        //用户登陆
        fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>>
    }

    interface ILoginView : BaseContract.IBaseView{

        fun loginResult(userInfo: UserInfo)
    }

    interface ILoginPresenter : BaseContract.IBasePresenter<ILoginView> {

        fun login(mobile: String, pwd: String, pushId: String)
    }
}