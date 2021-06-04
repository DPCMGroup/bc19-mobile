package com.example.bc19mobile.contract

import com.example.bc19mobile.data.BookingWorkstation
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
        fun saveUser(user: User?)
        fun getUser(): User?
        fun saveBookingWorkstation(
            dataTesto: String,
            inizioTesto: String,
            fineTesto: String,
            stanzaTesto: String,
            dipTesto: String
        )
        fun getBookingWorkstation(): BookingWorkstation?
    }

    interface IModel : IModelContract{
        fun setUser(user: User?)
        fun getUser(): User?
        fun saveBookingWorkstation(
            dataTesto: String,
            inizioTesto: String,
            fineTesto: String,
            stanzaTesto: String,
            dipTesto: String
        )
        fun setBookingWorkstation(bookingWorkstation: BookingWorkstation?)
        fun getBookingWorkstation(): BookingWorkstation?
    }
}
