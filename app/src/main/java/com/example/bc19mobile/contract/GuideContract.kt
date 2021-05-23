package com.example.bc19mobile.contract

import com.example.bc19mobile.data.User
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/21
 * @Description input description
 **/
interface GuideContract {

    interface IView : IViewContract {
        fun updateGuideView(guideName: String)
    }

    interface IPresenter : IPresenterContract {
        fun saveUser(user: User?)
        fun showGuide()
        fun getUser(): User?
    }

    interface IModel : IModelContract {
        fun setUser(user: User?)
        fun getGuide(): String
        fun getUser(): User?
    }
}
