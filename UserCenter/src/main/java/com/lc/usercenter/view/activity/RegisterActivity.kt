package com.lc.usercenter.view.activity

import android.os.Bundle
import android.widget.Toast
import com.lc.baselibrary.ext.enable
import com.lc.baselibrary.ui.activity.BaseMvpActivity
import com.lc.usercenter.R
import com.lc.usercenter.contract.RegisterContract
import com.lc.usercenter.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Created by LC on 2019/5/15
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterContract.IRegisterView {
    override fun attachLayoutRes(): Int = R.layout.activity_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = RegisterPresenter()
        mPresenter?.attachView(this)
        //  在分支上修改内容
        initView()
    }

    private fun initView() {
        mRegisterBtn.enable(mMobileEt) {isBtnEnable()}
        mRegisterBtn.enable(mVerifyCodeEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdConfirmEt) {isBtnEnable()}

        mRegisterBtn.setOnClickListener {
            mPresenter?.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }
    }

    override fun registerResult(boolean: Boolean) {
        toast("登陆成功$boolean")
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and mVerifyCodeEt.text.isNullOrEmpty().not() and mPwdEt.text.isNullOrEmpty().not() and mPwdConfirmEt.text.isNullOrEmpty().not()
    }

}
