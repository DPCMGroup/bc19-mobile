package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.BookingContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/13
 * @Description input description
 **/
class BookingPresenter : BaseMvpPresenter<BookingContract.IView, BookingContract.IModel>(),
    BookingContract.IPresenter {

    override fun onCreate() {
        super.onCreate()
        getModel().setBookingListener(object : BookingModel.BookingListener {
            override fun onBookingSuccess() {
                getMvpView().updateBookingView(getModel().getBookingList())
            }

            override fun onBookingFailure() {
                getMvpView().callError()
            }

            override fun onDeleteSuccess() {
                getMvpView().updateBookingDeleteView(getModel().getBookingList())
            }

            override fun onDeleteFailure() {
                getMvpView().callDeleteError()
            }
        })

    }

    override fun registerModel() = BookingModel::class.java
    override fun showBookings() {
        getModel().retriveBookingList()

    }

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun deleteBooking(bookId: Int) {
        getModel().deleteBooking(bookId)
    }

}
