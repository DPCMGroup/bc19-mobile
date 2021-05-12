package com.example.bc19mobile.presenter

import com.example.bc19mobile.contract.LoginContract
import com.example.bc19mobile.model.LoginModel
import mvp.ljb.kt.presenter.BaseMvpPresenter

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
class LoginPresenter : BaseMvpPresenter<LoginContract.IView, LoginContract.IModel>(), LoginContract.IPresenter{

    override fun onCreate() {
        super.onCreate()
        getModel().setLoginListener(object : LoginModel.LoginListener {
            override fun onLoginSuccess(): Void {
                TODO("Not yet implemented")
            }
        })
    }

    override fun registerModel() = LoginModel::class.java
    override fun makeLogin(username: String, password: String) {
        getModel().sendLogin(username, password)
    }



}
