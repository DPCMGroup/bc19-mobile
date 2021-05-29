package com.example.bc19mobile.contract

import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.User
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
interface BookingWorkstationContract {

    interface IView : IViewContract

    interface IPresenter : IPresenterContract{
        fun showAvailability()
        fun saveUser(user: User?)
        fun saveBookingWorkstation(bookingWorkstation: BookingWorkstation?)
        fun getUser(): User?
        fun getBookingWorkstation(): BookingWorkstation?
    }

    interface IModel : IModelContract{
        fun setUser(user: User?)
        fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?)
        fun getUser(): User?
        fun getBookingWorkstation(): BookingWorkstation?
        fun getAvailability()
    }
}
