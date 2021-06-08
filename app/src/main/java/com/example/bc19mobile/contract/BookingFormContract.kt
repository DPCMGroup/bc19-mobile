package com.example.bc19mobile.contract

import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.DataDirtyRooms
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingFormModel
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
interface BookingFormContract {

    interface IView : IViewContract{
        fun updateRoomsView(rooms: ArrayList<DataDirtyRooms>?)
        fun callErrorRooms()
    }

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
        fun getRooms()
    }

    interface IModel : IModelContract{
        fun getRooms()
        fun getRoomsList(): ArrayList<DataDirtyRooms>?
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
        fun setRoomsListener(listener: BookingFormModel.RoomsListener?)
    }
}
