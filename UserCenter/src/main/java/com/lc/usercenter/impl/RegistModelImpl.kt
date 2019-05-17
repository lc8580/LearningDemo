package com.lc.usercenter.impl

import com.lc.baselibrary.data.model.BaseResp
import com.lc.baselibrary.data.net.RetrofitFactory
import com.lc.baselibrary.mvp.BaseModel
import com.lc.usercenter.contract.RegisterContract
import com.lc.usercenter.data.api.UserApi
import com.lc.usercenter.data.req.RegisterReq
import io.reactivex.Observable

/**
 * Created by LC on 2019/5/16
 */
class RegistModelImpl : BaseModel(),RegisterContract.IRegisterModel {

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.getInstance().create(UserApi::class.java).register(RegisterReq(mobile, pwd, verifyCode))
    }

}