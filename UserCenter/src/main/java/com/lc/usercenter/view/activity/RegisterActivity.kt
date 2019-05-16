package com.lc.usercenter.view.activity

import android.os.Bundle
import com.lc.baselibrary.view.activity.BaseMvpActivity
import com.lc.usercenter.presenter.view.RegisterView
import com.lc.usercenter.R
import com.lc.usercenter.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Created by LC on 2019/5/15
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //  在分支上修改内容
        initView()
    }

    private fun initView() {
        mRegisterBtn.setOnClickListener {
            toast("注册")
        }
    }
}
