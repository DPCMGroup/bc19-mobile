package com.example.bc19mobile.view.act

import com.example.bc19mobile.contract.LoginContract
import com.example.bc19mobile.presenter.LoginPresenter
import mvp.ljb.kt.act.BaseMvpActivity
import com.example.bc19mobile.R

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
class LoginActivity : BaseMvpActivity<LoginContract.IPresenter>() , LoginContract.IView {

    override fun registerPresenter() = LoginPresenter::class.java

    override fun getLayoutId() = R.layout.activity_login

}
