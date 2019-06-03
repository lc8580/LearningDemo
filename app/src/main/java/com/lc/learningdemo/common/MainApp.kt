package com.lc.learningdemo.common

import com.lc.baselibrary.common.BaseApp

/*
    主工程 Application
 */
class MainApp: BaseApp() {
    override fun onCreate() {
        super.onCreate()

        //极光推送初始化
//        JPushInterface.setDebugMode(true)
//        JPushInterface.init(this)
    }
}


