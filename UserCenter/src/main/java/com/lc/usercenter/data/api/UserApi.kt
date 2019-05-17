package com.lc.usercenter.data.api

import com.lc.baselibrary.data.model.BaseResp
import com.lc.usercenter.data.req.RegisterReq
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by LC on 2019/5/16
 */
interface UserApi {

    /*
        用户注册
     */
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq): Observable<BaseResp<String>>
}