package com.example.bc19mobile.contract

import com.example.bc19mobile.data.User
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/24
 * @Description input description
 **/
interface CleanContract {

    interface IView : IViewContract

    interface IPresenter : IPresenterContract{
        fun showRooms()
        fun showWorkstations()
        fun getUser(): User?
        fun saveUser(user: User?)
    }

    interface IModel : IModelContract{
        fun getRooms()
        fun getWorkstations()
        fun setUser(user: User?)
        fun getUser(): User?
    }
}
