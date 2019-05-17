package com.lc.baselibrary.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.find

/**
 * Created by LC on 2019/5/15
 */
abstract class BaseActivity : RxAppCompatActivity() {

    /**
     * 布局文件id
     */
    @LayoutRes
    protected abstract fun attachLayoutRes(): Int
    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(attachLayoutRes())
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
//        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
//        AppManager.instance.finishActivity(this)
    }

    //获取Window中视图content
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }


}