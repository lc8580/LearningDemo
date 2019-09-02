package com.lc.usercenter.impl

import com.lc.baselibrary.data.model.BaseResp
import com.lc.baselibrary.data.net.RetrofitFactory
import com.lc.baselibrary.mvp.BaseModel
import com.lc.usercenter.contract.LoginContract
import com.lc.usercenter.data.api.UserApi
import com.lc.usercenter.data.model.UserInfo
import com.lc.usercenter.data.req.LoginReq
import io.reactivex.Observable

/**
 * Created by LC on 2019/5/16
 */
class LoginModelImpl : BaseModel(), LoginContract.ILoginModel {
    override fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.getInstance().create(UserApi::class.java).login(LoginReq(mobile, pwd, pushId))
    }
}