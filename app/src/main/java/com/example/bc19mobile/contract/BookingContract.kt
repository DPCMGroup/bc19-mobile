package com.example.bc19mobile.contract

import com.example.bc19mobile.data.DataBooking
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingModel
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
interface BookingContract {

    interface IView : IViewContract {
        fun updateBookingView(bookings: ArrayList<DataBooking>?)
        fun callError()
    }

    interface IPresenter : IPresenterContract {
        fun showBookings()
        fun saveUser(user: User?)
        fun getUser(): User?
        fun deleteBooking(bookId: Int)
    }

    interface IModel : IModelContract {
        fun getBookingList(): ArrayList<DataBooking>?
        fun setBookingListener(listener: BookingModel.BookingListener?)
        fun setUser(user: User?)
        fun getUser(): User?
        fun retriveBookingList()
        fun getBookId(bookId:Int)
    }
}
