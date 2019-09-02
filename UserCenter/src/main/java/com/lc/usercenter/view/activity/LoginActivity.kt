package com.lc.usercenter.view.activity

import android.os.Bundle
import android.view.View
import com.lc.baselibrary.ext.enable
import com.lc.baselibrary.ui.activity.BaseMvpActivity
import com.lc.baselibrary.utils.Preference
import com.lc.usercenter.R
import com.lc.usercenter.contract.LoginContract
import com.lc.usercenter.data.model.UserInfo
import com.lc.usercenter.presenter.LoginPresenter
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.mMobileEt
import kotlinx.android.synthetic.main.activity_register.mPwdEt
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by LC on 2019/5/15
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.ILoginView, View.OnClickListener {
    override fun attachLayoutRes(): Int = R.layout.activity_register

    private var mUserIcon: String? by Preference("sp_user_icon", "")
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = LoginPresenter()
        mPresenter?.attachView(this)
        //  在分支上修改内容
        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

        mLoginBtn.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)

        mRegisterBtn.setOnClickListener {
            mPresenter?.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
        }
    }

    /*
    点击事件
 */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mLoginBtn -> {
//                mPresenter?.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mPushProvider?.getPushId()
//                        ?: "")
            }
            R.id.mForgetPwdTv -> {
//                startActivity<ForgetPwdActivity>()
            }
        }
    }

    override fun loginResult(userInfo: UserInfo) {
        toast("登陆成功$userInfo")
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and mVerifyCodeEt.text.isNullOrEmpty().not() and mPwdEt.text.isNullOrEmpty().not() and mPwdConfirmEt.text.isNullOrEmpty().not()
    }

}
