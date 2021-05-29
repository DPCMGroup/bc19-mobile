package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.data.BookingWorkstation
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingWorkstationModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
class BookingWorkstationPresenter : BaseMvpPresenter<BookingWorkstationContract.IView, BookingWorkstationContract.IModel>(), BookingWorkstationContract.IPresenter{

    override fun registerModel() = BookingWorkstationModel::class.java

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }
    override fun saveBookingWorkstation(bookingWorkstation: BookingWorkstation?) {
        getModel().setBookingWorkstation(bookingWorkstation)
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun getBookingWorkstation(): BookingWorkstation? {
        return getModel().getBookingWorkstation()
    }

    override fun showAvailability() {
        getModel().getAvailability()
    }

}
