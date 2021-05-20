package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.BookingFormContract
import com.example.bc19mobile.data.User
import com.example.bc19mobile.model.BookingFormModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/20
 * @Description input description
 **/
class BookingFormPresenter : BaseMvpPresenter<BookingFormContract.IView, BookingFormContract.IModel>(), BookingFormContract.IPresenter{

    override fun registerModel() = BookingFormModel::class.java

    override fun saveUser(user: User?) {
        getModel().setUser(user)
    }

    override fun getUser(): User? {
        return getModel().getUser()
    }

    override fun showAvailability(
        date: String,
        startTime: String,
        endTime: String,
        room: String,
        colleague: String
    ) {
        TODO("Not yet implemented")
    }

}
