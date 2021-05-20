package com.example.bc19mobile.contract

import com.example.bc19mobile.data.User
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
interface BookingFormContract {

    interface IView : IViewContract

    interface IPresenter : IPresenterContract{
        fun showAvailability(date: String, startTime: String, endTime: String, room: String, colleague: String)
        fun saveUser(user: User?)
        fun getUser(): User?
    }

    interface IModel : IModelContract{
        fun setUser(user: User?)
        fun getUser(): User?
    }
}
