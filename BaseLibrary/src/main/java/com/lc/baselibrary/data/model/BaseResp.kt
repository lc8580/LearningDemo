package com.lc.baselibrary.data.model

/*
    能用响应对象
    @status:响应状态码
    @message:响应文字消息
    @data:具体响应业务对象
 */
data class BaseResp<out T>(val code:Int, val message:String, val data:T)
