package com.lc.baselibrary.common

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

open class BaseApp : Application() {

//    lateinit var appComponent: AppComponent

    companion object {

        @JvmField
        val TAG = "BaseApp"
        var instance: Context by Delegates.notNull()
            private set

    }

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        instance = this

        //ARouter初始化
//        ARouter.openLog()    // 打印日志
//        ARouter.openDebug()
//        ARouter.init(this)
    }

    /*
        Application Component初始化
     */
    private fun initAppInjection() {
//        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}