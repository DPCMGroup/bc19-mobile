package com.example.bc19mobile.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.example.bc19mobile.contract.BookingWorkstationContract
import com.example.bc19mobile.model.BookingWorkstationModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/05/29
 * @Description input description
 **/
class BookingWorkstationPresenter : BaseMvpPresenter<BookingWorkstationContract.IView, BookingWorkstationContract.IModel>(), BookingWorkstationContract.IPresenter{

    override fun registerModel() = BookingWorkstationModel::class.java

}
