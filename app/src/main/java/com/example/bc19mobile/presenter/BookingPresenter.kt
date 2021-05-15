package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.BookingContract
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
            override fun onBookingSuccess(): Void {
                //qui siamo nel mainThread
                //chiamo la vista cambio pannello e faccio le cose che devo fare
                TODO("Not yet implemented")
            }
        })

    }

    override fun registerModel() = BookingModel::class.java
    override fun showBookings() {
        getModel().getBookingList()

    }

}
