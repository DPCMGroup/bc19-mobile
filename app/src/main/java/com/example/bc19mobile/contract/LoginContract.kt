package com.example.bc19mobile.contract

<<<<<<< HEAD
import com.example.bc19mobile.model.LoginModel
=======
import com.example.bc19mobile.data.User
>>>>>>> 363dd35cdd397dd233f12d82ad6f23cc262c6ede
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/04/23
 * @Description input description
 **/
interface LoginContract {

    interface IView : IViewContract {
    }

    interface IPresenter : IPresenterContract {
        fun makeLogin(username: String, password: String)
    }

    interface IModel : IModelContract {
        fun sendLogin(username: String, password: String)
        fun setLoginListener(listener: LoginModel.LoginListener?)
        fun getUser(): User?
    }
}
