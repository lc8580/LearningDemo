package com.lc.baselibrary.rx

import com.lc.baselibrary.rx.scheduler.IoMainScheduler

/**
 * Created by chenxz on 2018/4/21.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()

}